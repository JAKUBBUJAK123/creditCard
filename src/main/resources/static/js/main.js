const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json())
}

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json() ) ;
}

const addProduct = (productId) => {
    return fetch(`/api/add-to-cart/${productId}`, {
        method: 'POST'
    });
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    })
        .then(response => response.json())
}

const checkoutForm = document.querySelector(".checkoutEl")
checkoutForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstname: checkoutForm.querySelector('input[name="fname"].value'),
        lastname: checkoutForm.querySelector('input[name="lname"].value'),
        email: checkoutForm.querySelector('input[name="email"].value')
    }

    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => {
            console.log(reservationDetails)
            window.location.href = reservationDetails.paymentURL;
        })
})

const createProductHtml = (productData) => {
    const template = `
            <div>
                <h4>${productData.name}</h4>
                <span>${productData.price}</span>
                <img src="https://picsum.photos/id/237/200/300" />
                <button data-id="${productData.id}">Add to cart</button>
            </div>
        `;

    const productEl = document.createElement('li');
    productEl.className = "product";
    productEl.innerHTML = template.trim();

    return productEl;
}

const refreshOffer = () => {
    const totalEl = document.querySelector(".offer__total");
    const itemsEl = document.querySelector(".offer__itemsCount");

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemsEl.textContent = `${offer.itemsCount} Items`;
        });
}

const initializerCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click", () => {
        console.log("It works")
        const productId = event.target.getAttribute("data-id")


        addProduct(productId)
            .then(() => refreshOffer())
    })

    return productHtmlEl;
}

document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(createProductHtml))
        .then(productsAsHtmlEl => productsAsHtmlEl.map(initializerCartHandler))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl));
        })
    refreshOffer()
});


