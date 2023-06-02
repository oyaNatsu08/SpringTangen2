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
            updateView: false,
            productId: '',
            productName: '',
            price: '',
            description: '',
            categoryList: [],
            category: '',
            imgPath: '',
            text: '',
            Error: [],
            imgError: '',
            updateError: [],
            imagePathTmp: [],
            // idError: '',
            // nameError: '',
            // priceError: '',
            // categoryError: '',
            // descriptionError: '',
            // imgError: '',
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
                    this.text = '';
                    this.products = res.data;
                    this.count = this.products.length;
                })
        },
        addViewSet() {
            this.menuView = false;
            this.addView = true;
            this.text = '';
            this.categoryGet();

        },
        categoryGet() {
            axios.get('/api/category-list')
                .then(res => {
                    this.categoryList = res.data;
                })
        },
        back() {
            this.addView = false;
            this.detailView = false;
            this.updateView = false;
            this.menuView = true;
            this.getProductList();
            this.productId = '';
            this.productName = '';
            this.price = '';
            this.description = '';
            this.category = '';
            this.imgPath = '';
            this.Error = [];
            this.imgError = '';
            this.successDel = '';
        },
        addProduct() {
            const judge = window.confirm('登録しますか？');
            if (judge) {
                console.log(this.imgPath);
                axios.post('/api/product', {
                    // addList: {
                    id: null,
                    productId: this.productId,
                    category: this.category,
                    name: this.productName,
                    price: this.price,
                    imagePath: this.imgPath,
                    description: this.description,
                    createRd: null,
                    createUd: null,
                    // }
                })
                    .then(res => {
                        //画像アップロード
                        this.uploadImage(this.imagePathTmp);
                        this.back();
                        this.text = '登録が完了しました';
                    })
                    .catch(error => {
                        // エラーメッセージを表示
                        this.Error = error.response.data.split(',');
                        //console.log(this.Error);
                    });
                // .catch(error => {
                //     console.error(error);
                //     // エラーハンドリングの処理を追加することもできます
                // });

            }
        },
        imagePath(event) {
            console.log(event);
            const file = event.target.files[0];
            // ファイルパスを取得してimgPathにセットする
            this.imgPath = URL.createObjectURL(file);
            console.log('画像パス：' + this.imgPath);
        },
        setImage(event) {
            this.imagePathTmp = event.target.files[0];
            //console.log(event.target.files[0]);
        },
        uploadImage(file) {
            const formData = new FormData();
            formData.append('image', file);
            formData.append('id', this.productId);

            axios.post('/api/upload-image', formData)
                .then(res => {
                    // 画像のアップロードが成功した場合の処理
                    console.log('画像がアップロードされました');
                })
                .catch(error => {
                    // エラーメッセージを表示
                    this.imgError = '画像のアップロード中にエラーが発生しました';
                    console.error('画像のアップロード中にエラーが発生しました', error);
                });
        },
        detailViewSet(product) {
            this.menuView = false;
            this.addView = false;
            this.detailView = true;
            this.text = '';

            const targetProduct = product;
            this.imgPath = targetProduct.imgPath;
            this.productId = targetProduct.productId;
            this.productName = targetProduct.name;
            this.price = targetProduct.price;
            this.description = targetProduct.description;
            this.category = targetProduct.category;

            //productIdをもとにidを取得する
            this.idGet();

        },
        idGet() {
            axios.get('/api/id', {
                params: {
                    productId: this.productId
                }
            })

                .then(res => {
                    this.id = res.data.id;
                    //console.log(this.id);
                })
        },
        updateViewSet() {
            this.menuView = false;
            this.addView = false;
            this.detailView = false;
            this.updateView = true;
            this.categoryGet();
        },
        updateProduct() {

            axios.put('/api/update', {
                id: this.id,
                productId: this.productId,
                category: this.category,
                name: this.productName,
                price: this.price,
                imagePath: this.imgPath,
                description: this.description,
                createRd: null,
                createUd: null,
            })
                .then(res => {
                    //画像アップロード
                    this.uploadImage(this.imagePathTmp);
                    this.back();
                    this.text = '更新処理が完了しました';
                })
                .catch(error => {
                    // エラーメッセージを表示
                    this.Error = error.response.data.split(',');
                    //console.log(this.Error);
                });
        },
        deleteViewSet(productId) {
            const judge = window.confirm('削除しますか？');
            if (judge) {
                axios.delete('/api/delete', {
                    params: {
                        id: productId
                    }
                })
                    .then(res => {
                        this.back();
                        this.text = '削除処理が完了しました';
                    });
            }

        }
        // formValidate() {
        //     this.addError = '';
        //     this.idError = '';
        //     this.nameError = '';
        //     this.priceError = '';
        //     this.categoryError = '';
        //     this.descriptionError = '';
        //     this.imgError = '';

        //     if (!this.productId) {
        //         this.idError = '商品IDを入力してください';
        //     }

        //     if (!this.productName) {
        //         this.nameError = '商品名を入力してください';
        //     }

        //     if (!this.price) {
        //         this.priceError = '価格を入力してください';
        //     }

        //     if (!this.category) {
        //         this.categoryError = 'カテゴリを選択してください';
        //     }

        //     if (!this.imgPath) {
        //         this.imgError = '画像を選択してください';
        //     }

        //     if (this.productId.length < 1 || this.productId.length > 20) {
        //         this.idError += '  商品IDは1～20文字で入力してください';
        //     }

        //     if (this.productName.length < 1 || this.productName.length > 255) {
        //         this.idError += '  商品IDは1～255文字で入力してください';
        //     }

        //     // if (!'^\d{7}$/'.test(this.price)) {
        //     //     this.priceError += '桁が大きすぎます';
        //     // }

        //     if (this.productId.length > 2000) {
        //         this.descriptionError += '  商品詳細は2000文字以内で入力してください';
        //     }
        // },
    },
    created: function () {
        this.getProductList();
    }
}).mount('#app')