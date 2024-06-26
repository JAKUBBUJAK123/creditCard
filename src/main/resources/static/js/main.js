
//API CONTEXT
console.log("test222")
const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json())
}

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const addProduct = (productId) => {
    return fetch(`/api/add-to-cart/${productId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Add headers if necessary
        }
    })
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

const checkoutForm = document.querySelector(".checkout__form")
checkoutForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstname: checkoutForm.querySelector('input[name="fname"]').value,
        lastname: checkoutForm.querySelector('input[name="lname"]').value,
        email: checkoutForm.querySelector('input[name="email"]').value
    }

    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => {
            console.log(reservationDetails)
            window.location.href = reservationDetails.paymentURL;
        })
});








const createProductHtml = (productData) => {
    const template = `
        <div class="product">
            <div class="product__left">
                <img src="https://picsum.photos/id/237/200/300" alt="">
            </div>
            <div class="product__right">
                <div class="product__name">${productData.name}</div>
                <div class="product__description">${productData.description}</div>
                <div class="product__price">Cena: ${productData.price} PLN</div>
                <div class="product__button">
                    <button data-id="${productData.id}">Add</button>
                </div>
            </div>
        </div>
    `

    const productEl = document.createElement('div');
    productEl.className = "product";
    productEl.innerHTML = template.trim();

    return productEl;
}

const refreshOffer = () => {
    const totalEl = document.querySelector(".offer__total")
    const itemsEl = document.querySelector(".offer__itemsCount")

    getCurrentOffer()
        .then(offer => {
                totalEl.textContent = `${offer.total} PLN`;
                itemsEl.textContent = `${offer.itemsCount} Items`;
            }
        )

}

const initializerCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button");
    addToCartBtn.addEventListener("click", () => {
        const productId = addToCartBtn.getAttribute("data-id");
        console.log('Adding product with ID:', productId); // Log product ID for debugging

        addProduct(productId)
            .then(() => refreshOffer())
    })

    return productHtmlEl;
}


document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector(".catalog");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(createProductHtml))
        .then(productsAsHtmlEl => productsAsHtmlEl.map(initializerCartHandler))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl));
        })
    refreshOffer()
});

