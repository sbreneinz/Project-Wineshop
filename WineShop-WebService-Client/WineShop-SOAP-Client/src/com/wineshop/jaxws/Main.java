package com.wineshop.jaxws;


public class Main {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        Product servicePort = service.getProductPort();
        
        // Get all products
        System.out.println(servicePort.getProducts());
        
        System.out.println("-------------------\n");
        
        // Get product with ID 24
        System.out.println(servicePort.getProductById("24"));
        
        System.out.println("-------------------\n");
        
        // Get product with Name
        System.out.println(servicePort.getProductsByName("Gloria Reynolds"));
    }
}

/* 
                OUTPUT (from my DB @ 20/09/2021)

Id: 1 Name: Gloria Reynolds Description: Julian, son of Gloria, produces a quality wine, named after his mother Gloria Reynolds. Price: 69.9 Type: Red Year: 2005 Country: Portugal Region: Alentejo Producer: Reynold Wine Growers 
Id: 2 Name: Chateau Petrus Description: Petrus is the biggest name in a star constellation of Bordeaux. Located on the right bank of Bordeaux in the small "Apellation" of "Pomerol", where the Merlot variety is queen. Until 1945 this wine was in the so-called "secret of the gods". In possession of Madame Loubat, which was joined by Jean Pierre Moueix. Wine began to be unveiled, whether or not it was the preferred wine of names like the Kennedys or Queen Elizabeth of England. Due to a relentless demand on the part of collectors and investors, combined with a very limited production, this wine is today one of the most expensive in the world. It is the wine that anyone would like to taste at least once in their life. A dream of wine. Price: 7490.0 Type: Red Year: 1945 Country: France Region: Bordeaux Producer: Chateau Petrus 
Id: 3 Name: Barca Velha Description: Barca-Velha is the epitome, the first, unquestionable symbol of the highest quality of Douro wines. Classic, intense, complex, elegant and rich, there are few adjectives to describe what is, since its creation in 1952, the most celebrated Portuguese wine. Barca-Velha is the foundation on which the reputation of Casa Ferreirinha was built, the brand with the greatest quality tradition in the Douro and one of the main world references Price: 679.0 Type: Red Year: 1999 Country: Portugal Region: Douro Producer: Sogrape Vinhos 
Id: 4 Name: Pera Manca Description: They are full-bodied, complex and elegant wines, with an aroma of raisins of fruits and essences from the aging woods. Due to the high quality of the tannins and woods used, these are wines that have great longevity, requiring some time to reveal their full potential. Price: 550.0 Type: Red Year: 2011 Country: Portugal Region: Alentejo Producer: Fundacao Eugenio de Almeida 
Id: 5 Name: Dom Perignon Description: Dom Perignon is vintage champagne only. Each vintage is a creation, singular and unique, that expresses both the character of the year, and the character of Dom P�rignon. After at least eight years of elaboration in the cellars, the wine embodies the perfect balance of Dom P�rignon, the Pl�nitude of harmony. Price: 178.9 Type: Champagne Year: 2010 Country: France Region: Champagne Producer: Moet & Chandon 
Id: 6 Name: Ruinart Rose Description: Presented in an elegant 18th century style bottle, this champagne reveals a fresh and distinctive rose color. Varieties: Premier Cru Chardonnay 45% and Premier Cru Pinot Noir 55%. Very fine fruity aroma (blackcurrant and blackberry). A very intense wine. Vigorous and well-balanced palate. A perfect, smooth wine with cherry notes. Quite long finish. Price: 89.9 Type: Champagne Year: 2012 Country: France Region: Champagne Producer: Ruinart 
Id: 7 Name: Chablis Beauroy Description: All wines produced by Maison Louis Jadot are “Appellation dOrigine Contrôlée wines from across the region. Maison Louis Jadot now controls 240 hectares spread across Burgundy, from Côte dOr to Mâconnais and even Beaujolais. Thanks to the quality of their wines, they have forged strong links, both in France and around the world, with sommeliers, restaurateurs, wine merchants, importers, agents and wine lovers. Price: 43.9 Type: White Year: 2017 Country: France Region: Bourgogne Producer: Maison Louis Jadot 
Id: 8 Name: Tapada do Chaves Vinhas Velhas Description: It is in the outskirts of Portalegre, in Alto Alentejo, that Tapada do Chaves wines are produced. For almost 100 years, this property has produced wines of recognized quality that are among the best in Portugal. Tapada do Chaves wines preserve the warmth and smoothness of the Alentejo region. Associated with a strong family tradition and a history of passion and dedication to the land, they originate from the vineyards of the property that gives them their name, aged between 15 and 85 years, from where red grapes of the Trincadeira, Aragonez, varieties come from. Castelão and Tinta Francesa and white varieties from Fernão Pires, Arinto, Alva and Tamarez. Price: 53.5 Type: White Year: 2008 Country: Portugal Region: Alentejo Producer: Tapada do Chaves 
Id: 9 Name: Quinta da Leda Description: Sogrape Vinhos was founded in 1942 by Fernando van Zeller Guedes, with the ambition of making Portuguese wines known to the world and a long-term vision based on the quality of the wines to be marketed, the importance of brand novelty and the presentation of its products. wines. Led today by the third generation of the founding family, Sogrape Vinhos increasingly faithfully fulfills the objective assumed since its foundation: to be a family-oriented company with an international vocation, focused on the production of quality wines, innovation and development of Portuguese brands of global level. Sogrape Vinhos has around 830 hectares of vineyards in Portugal. Casa Ferreirinha and its wines are synonymous with time and art. This has happened since its foundation, in the 18th century, by the hand of Bernardo Ferreira, who saw the formula refined by his descendants, especially by his granddaughter Dona Antónia Adelaide Ferreira, who affectionately became known as "Ferreirinha" or "Ferreirinha-da-Régua" for the people of your land. Through the hands of Dona Antónia, who twice widowed herself at the head of a large company, Ferreira consolidated itself admirably. Price: 36.5 Type: Red Year: 2017 Country: Portugal Region: Douro Producer: Casa Ferreirinha 
Id: 10 Name: Quinta Vale D. Maria Description: Quinta do Vale Dona Maria is a property situated by the mouth of the Rio Torto. Its history dates back to 1868 and was associated with names such as the Symingtons, who used their grapes to integrate into vintage Smith Woodhouse. When the Van Zeller family took over this farm, they found it almost completely abandoned, with about 10ha of old vines. The recovery work would be arduous, but nothing that would take Cristiano van Zeller away from this arduous task, or if he did not come from a superb "school" like Quinta do Noval, of which he was president. Work began on recovering the vineyard and houses that were almost reduced to dust. But what commanded Cristiano was to realize day after day that he had a pearl there that he had so much to give. They also acquired plots adjacent to the Quinta, until it reached 28ha of vineyard. The inaugural harvest was in 1996 and it never stopped producing great wines. Recently, the wines from this farm, CV, Vale Dona Maria, and the recent Vinha do Rio and Vinha Francisca have been among the best wines in Portugal for Wine Advocate, the most important and prestigious wine publication in the world. Price: 43.5 Type: Red Year: 2017 Country: Portugal Region: Douro Producer: Lemos & Van Zeller 
Id: 11 Name: Horta Osório Grande Escolha Description: The “H.O. – Horta Osório Wines” come exclusively from grapes produced on the farms of Casa Agrícola Horta Osório where, in addition to “old vineyards”, “new vineyards” were planted with the best traditional varieties of the Douro, in unique “terroirs”. The grapes are harvested when they have a perfect phenolic ripeness, which usually occurs on the 4th week of September. The entire harvest is done manually, which allows for a refined choice and selection. The transport of the grapes from the vineyard to the winery is done in small 20 kg containers. The entire winemaking process is accompanied, maintaining the fermentation at a controlled temperature, to ensure the high quality of the wines. Price: 42.9 Type: Red Year: 2015 Country: Portugal Region: Douro Producer: Horta Osório Wines 
Id: 12 Name: Monólogo Avesso Description: Monólogo Avesso P67 is a single variety wine produced at Quinta de Santa Teresa, from the vineyards of the P67 plot, using sustainable and organic farming practices. The Avesso variety is the native variety of Baião. Finding in this region its expression of excellence, with a climate of transition between the typical Atlantic climate of Vinho Verde and the continental climate of the Douro, this variety has the opportunity to reach, in a balanced way, complete levels of maturation, resulting in complex wines, delicate and long-lasting. Price: 12.0 Type: White Year: 2020 Country: Portugal Region: Douro Producer: A&D Wines 
Id: 23 Name: Paulaner Description: Paulaner Weissbier Price: 3.0 Type: Weissbier Year: 2018 Country: Germany Region: Bavaria Producer: Paulaner Munchen 
Id: 24 Name: Duvel Description: Duvel Belgian Bier Price: 3.6 Type: Belgian Beer Year: 2018 Country: Belgium Region:  Puurs-Sint-Amands Producer: DUVEL MOORTGAT BELGIUM 
Id: 25 Name: C Description: C Price: 2.0 Type: Red Year: 2018 Country: Belgium Region:  d Producer: a 
Id: 26 Name: San Pellegrino Description: Italian sparkling Water Price: 5.0 Type: Sparkling Water Year: 2019 Country: Italy Region: Italian Alps Producer: San Pellegrino 

-------------------

########################
Id: 24
Name: Duvel
Description: Duvel Belgian Bier
Price: 3.6€
Type: Belgian Beer
Year: 2018
Country: Belgium
Region:  Puurs-Sint-Amands
Producer: DUVEL MOORTGAT BELGIUM
########################

-------------------

Id: 1 Name: Gloria Reynolds Description: Julian, son of Gloria, produces a quality wine, named after his mother Gloria Reynolds. Price: 69.9 Type: Red Year: 2005 Country: Portugal Region: Alentejo Producer: Reynold Wine Growers 
*/