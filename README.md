# Munro API

An API providing the finest Munro data. 

## Installation

Requires Java 15.
Clone this repo into your favourite java IDE.

## Usage

Run the application in your favourite java IDE send your GET requests to

```
localhost:8080/api/munros
```

available query parameters

```
sort = "nameAscending" || "nameDescending" || "heightAscending" || "heightDescending"
category = "MUN" || "TOP"
minHeight = number
maxHeight = number
limit = number
```
query parameters example

```
localhost:8080/api/munros?category=MUN&minHeight=980&maxHeight=1000&sort=nameDescending&limit=3
```


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.




