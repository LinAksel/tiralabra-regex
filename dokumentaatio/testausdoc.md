# Testausdokumentti

## Yksikkötestaus

JUnit-yksikkötestit löytyvät sekä TestValidator- että TestRegex-luokista, ja ne testaavat tulkin kummankin toiminnallisen pääosan toimintaan suhteellisen kattavasti. Testit voidaan ajaa komennolla

```
mvn test
```

Jacoco-raportin voi luoda omalle koneelleen komennolla

```
mvn test jacoco:report
```

## Suorityskykytestaus

Suorituskykytestausta löytyy Performance-luokasta. Luokka sisältää oman main-metodinsa, joten suorituskykytestit voi käynnistää suoraan komennolla

```
mvn compile exec:java -Dexec.mainClass=regex.Performance
```
Tämäntyyppistä testausta on huomattavasti vähemmän kuin yksikkötestausta, mutta olemassaolevat ovat kiinnostavia pahimman tapauksen aikavaativuuden osoittamiseksi.

## Testituloksia

### Yksikkötestit

Yksikkötestauksen perusteella kaikkien [käyttöohjeessa](/dokumentaatio/kayttikset.md) mainittujen ominaisuuksien tulisi toimia tällä hetkellä oikein,
ja erilaisia yhdistelmiä on koettu testata kattavasti. Testit ovat nopeita, sillä säännölliset lausekkeet ovat tarkoituksella pieniä, yhtä tai muutamaa ominaisuutta nimenomaisesti testaavia.

![testcoverage.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/testcoverage.png)

* Jacoco-raportin perusteella haaraumakattavuus on nyt täydellinen kummallakin testattavalla luokalla, ja testejä lisätään edelleen, mikäli uusia yhdistelmiä ja ominaisuuksia keksitään.  

### Suorituskykytestit
Suorituskykytestauksessa testataan oletuksena tällä hetkellä "pahimman mahdollisen" regexrakenteen, jatkuvien Kleenen tähtien, vaikutusta ohjelman suoritusaikaan. Katsotaan tarkemmin paria testiajoa:  

![ptest.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/ptest.png)

* Musta käyrä on todellinen suoritusaika, kun säännöllinen lauseke oli muotoa  
(.)\* -> ((.)\*)\* -> (((.)\*)\*)\* -> ... ,  
eli minkä tahansa merkin hyväksyvälle merkille '.' rakennettiin ympärille uusia, Kleenen tähdellä merkittyjä sulkuryhmiä. Verrattavana merkkijonona oli vain kirjain 'a'. Vihreä, punainen ja sininen edustavat aineistoon sovitettuja ensimmäisen, toisen ja kolmannen asteen polynomikäyriä tässä järjestyksessä. Huomataan selvästi, että kolmannen asteen käyrä on pienin aineistoon todella hyvin sopiva. Tiesimme ohjelman aikavaativuuden olevan O(n<sup>3</sup>), ja tulokset näyttävät tukevan tätä.

![ptest2.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/ptest2.png)

* Kuten ylemmässä, vihreä on ensimmäisen, punainen toisen ja sininen kolmannen asteen sovitettu polynomikäyrä. Tällä kertaa ajettuna oli kolme testiä, ensin Kleenen tähdillä kuten yllä, sitten '+'-merkillä  
(.)\+ -> ((.)\+)\+ -> (((.)\+)\+)\+ -> ... ,  
jonka teoreettinen aikavaativuus on O(n<sup>2</sup>), ja lopulta vain suluilla  
(.) -> ((.)) -> (((.))) -> ... ,  
rakennettu regex, jonka aikavaativuuden tulisi olla lineaarinen. Vertailtavana merkkijonona oli jälleen vain kirjain 'a'. Nämä oletukset vaikuttavat toteutuvan: '+'-merkeistä koostuva regex suoritetaan huomattavasti nopeammin kuin '*'-merkeistä koostuva, ja ilman toistoja suoritusaika on lähes sama (~0ms) koko ajan.

Suorituskykytestauksen toinen osa tutkii vertailtavan merkkijonon pituuden vaikutusta suoritusaikaan. Tulokset näyttävät selvästi, että merkkijonon pituus m ei vaikuta suoritusaikaan, sillä aika pysyy samana koko testiskaalalla, joten sen kummempaa analyysia tästä ei tarvita.