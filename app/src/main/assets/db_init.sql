CREATE TABLE recipes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    category TEXT,
    name TEXT,
    ingredients TEXT,
    instructions TEXT
);

INSERT INTO recipes VALUES
(null, "CHRISTMAS", "Janssons frestelse", "Potatis, grädde, ansjovis, kryddor", "1. Strimla potatisen, tillsätt resten.\n2. Gör lite saker.\n3. Grädda i ugnen en lång stund."),
(null, "RECIPEFORDISASTER", "Using git for the first time", "Git, Android Studio, Java, Sqlite", "Make changes simultaneously.\nDon't resolve the conflicts.\nJust commit and push directly to main all at once.");
