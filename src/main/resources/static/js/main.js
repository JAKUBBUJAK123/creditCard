getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}


getCurrentOffer = () => {
    return fetch("api/current-offer")
        .then(response => response.json());
}

const addProduct(productId) => {
return fetch(`/api/add-product/${productId}`, {method: "POST"})
       .then(response => response.json());
}

const acceptOffer(acceptOfferRequest) {
return fetch("api/accept-offer" , {method: "POST"} , headers: { "Content-Type" : "application/json"} , bod: JSON.stringify(acceptOfferRequest))
.then(response => response.json());
}

const createProductHtml = (productData) => {

    const template = `
        <div>
            <h4>${productData.name}</h4>
            <span>${productData.price}</span>
            <img src="https://picsum.photos/id/237/200/300"/>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    `;

    const productEl = document.createElement('li');
    productEl.innerHTML = template.trim();

    return productEl;
}
const refreshOffer() => {
    const totalEL = document.querySelector(".offer__total");
    const itemEL = document.querySelector(".offer_ItemsCount");
    getCurrentOffer()
    .then(offer => {
    totalEL.textContent = '%{offer.total} PLN';
    itemEL.textContent = "%{offer.itemsCount} Items"
    })
}


const initializeCartHandler = (productHtmlEL) => {
    const addToCartBtn = productHtmlEL.querySelector("button");
    addToCartBtn.addEventListener("click", () =>{
        const productId = addTOCartBtn.getAttribute("data-id");
        addProduct(productId)
        .then(refreshOffer());

        console.log("Im going to add product %{productId}")
    })

    return productHtmlEL;
}

document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(createProductHtml))
        .then(productsAsHtml => productsAsHtml.map(initializeCartHandler))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl));
            refreshOffer();
        })
});