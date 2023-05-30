const { createApp } = Vue

createApp({
    data() {
        return {
            count: 0,
            products: [],
            search: '',
            menuView: true,
            addView: false,
            detailView: false,
            productId: '',
            productName: '',
            price: '',
            description: '',
            categoryList: [],
            category: '',
            imgPath: '',
            text: '',
            addError: '',
            idError: '',
            nameError: '',
            priceError: '',
            categoryError: '',
            imgError: '',
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
            this.getProductList();
            this.text = '';
        },
        addProduct() {
            const judge = window.confirm('登録しますか？');
            if (judge && this.formValidate()) {
                axios.post('/api/product', {
                    // addList: {
                    id: null,
                    productId: this.productId,
                    category: this.category,
                    name: this.productName,
                    price: this.price,
                    description: this.description,
                    imgPath: this.imgPath,
                    createRd: null,
                    createUd: null,
                    // }
                })
                    .then(res => {
                        this.text = '登録が完了しました';
                        this.productId = '';
                        this.productName = '';
                        this.price = '';
                        this.description = '';
                        this.category = '';
                        this.descriptionError = '';
                        this.imgPath = '';
                    })
                    .catch(error => {
                        console.error(error);
                        // エラーハンドリングの処理を追加することもできます
                    });

            }
        },
        imagePath(event) {
            console.log(event);
            const file = event.target.files[0];
            // ファイルパスを取得してimgPathにセットする
            this.imgPath = URL.createObjectURL(file);
        },
        detailViewSet() {
            this.menuView = false;
            this.addView = false;

        },
        formValidate() {
            this.addError = '';
            this.idError = '';
            this.nameError = '';
            this.priceError = '';
            this.categoryError = '';
            this.descriptionError = '';
            this.imgError = '';

            if (!this.productId) {
                this.idError = '商品IDを入力してください';
            }

            if (!this.productName) {
                this.nameError = '商品名を入力してください';
            }

            if (!this.price) {
                this.priceError = '価格を入力してください';
            }

            if (!this.category) {
                this.categoryError = 'カテゴリを選択してください';
            }

            if (!this.imgPath) {
                this.imgError = '画像を選択してください';
            }

            if (this.productId.length < 1 || this.productId.length > 20) {
                this.idError += '  商品IDは1～20文字で入力してください';
            }

            if (this.productName.length < 1 || this.productName.length > 255) {
                this.idError += '  商品IDは1～255文字で入力してください';
            }

            if (!'^\d{7}$/'.test(this.price)) {
                this.priceError += '桁が大きすぎます';
            }

            if (this.productId.length > 2000) {
                this.descriptionError += '  商品詳細は2000文字以内で入力してください';
            }
        },
    },
    created: function () {
        this.getProductList();
    }
}).mount('#app')