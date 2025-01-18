CREATE TABLE product
(
    id          INT PRIMARY KEY,
    titre       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image       VARCHAR(255) NOT NULL,
    prix        INT          NOT NULL
);

INSERT INTO Product (id, titre, description, image, prix) VALUES
                                                              (1, 'Product 1', 'First product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product1.png?raw=true', 20.00),
                                                              (2, 'Product 2', 'Second product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product2.png?raw=true', 25.00),
                                                              (3, 'Product 3', 'Third product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product3.png?raw=true', 30.00),
                                                              (4, 'Product 4', 'Fourth product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product4.png?raw=true', 35.00),
                                                              (5, 'Product 5', 'Fifth product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product5.png?raw=true', 40.00),
                                                              (6, 'Product 8', 'Eighth product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product8.png?raw=true', 45.00),
                                                              (7, 'Product 9', 'Ninth product in the catalog.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/product9.png?raw=true', 50.00),
                                                              (8, 'About', 'An elegant decorative vase.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/about.jpg?raw=true', 25.99),
                                                              (9, 'Laptop', 'Image of a laptop for tech-related products.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/leptop.jpg?raw=true', 899.99),
                                                              (10, 'PC', 'Image of a desktop PC.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/pc.png?raw=true', 699.99),
                                                              (11, 'PCT', 'Another PC-related image.', 'https://github.com/chilleflx/my-image-repo/blob/main/images/pct.png?raw=true', 699.99);