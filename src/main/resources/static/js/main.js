const { createApp } = Vue

createApp ({
    data() {
        return {
            count: 0,
            products: [],
            search: '',
            menuView: true,
            addView: false,
            productId: '',
            productName: '',
            price: '',
            description: '',
            categoryList: [],
            category: '',
        }
    },
    methods: {
        getProductList() {
            axios.get('/api/product-list')
            .then(res => {
                this.products = res.data;
                this.count = this.products.length;
            })
        },
        find(search) {
            axios.get(`/api/search-list?name=${search}`)
            .then(res => {
                this.products = res.data;
                this.count = this.products.length;
            })
        },
        addViewSet() {
            this.menuView = false;
            this.addView = true;
            axios.get('/api/category-list')
            .then(res => {
                this.categoryList = res.data;
            })
            
        },
        addBack() {
            this.addView = false;
            this.menuView = true;
        },
    },
    created: function () {
        this.getProductList();
    }
}).mount('#app')