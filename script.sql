/*scripts postgresql*/


CREATE SCHEMA ecommerce AUTHORIZATION postgres;



CREATE TABLE ecommerce.product (
    id uuid PRIMARY KEY,
    sku varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    product_type varchar(255) NOT NULL,
    price money NOT NULL
);


CREATE TABLE ecommerce.cart_item (
    id uuid PRIMARY KEY,
    product_id uuid NOT NULL,
    quantity int4 NOT NULL,
    shopping_cart_id uuid NOT NULL,
    CONSTRAINT cart_item_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT cart_item_shopping_cart_id_fkey FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id)
);


CREATE TABLE ecommerce.shopping_cart (
    id uuid PRIMARY KEY,
    status int4 NOT NULL
);


INSERT INTO ecommerce.product (id, name ,sku, description, price, product_type) 
    VALUES
    ('f4691d7f-3863-4fcd-8ab8-a8ff8a40e22d', 'cal hidraulica 30kg', 'CAL-CAC-SIM', 'cacique', '1531.86', 'simple'),
    ('f121dc9d-1749-4d53-937b-5ae16dc7f8cf', 'cemento tipo portland 50kg', 'CEM-LOM-SIM', 'loma negra', '1403.60', 'simple'),
    ('70a49d39-aa4f-4c2e-90d2-ce4010b33497', 'pintura impermeabilizante 20lts', 'PIN-CAS-DES', 'casablanca', '7596.04', 'descuento'),
    ('e2a30f2a-29b9-4913-ad6e-a54464925df1', 'membrana asfaltica 40kg', 'MEM-MEG-DES', 'megaflex', '2834.34', 'descuento'),
    ('83a355d2-66ff-4442-a7dc-546e866c1c55', 'ladrillo comun x unidad', 'LAD-CER-SIM', 'ceramica neuquen', '278.85', 'simple');
