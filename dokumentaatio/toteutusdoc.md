# Toteutus

## Rakenne

Ohjelma koostuu neljästä pakkauksesta, ja niiden sisältämien luokkien riippuvuudet ovat seuraavat:

![diagram.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/diagram.png)

Pakkausten sisällöstä hieman tarkemmin:

* regex-kansio pitää sisällään luokat Main, MainTUI ja Performance. Näistä molemmat Main-luokat toimivat vain käynnistäjinä käyttöliittymälle. Performance-luokka kuuluu pakkaukseen siksi, että sillä myös sillä on oma, erillinen main-funktionsa. Muista poiketen se sisältää kuitenkin suorituskykytestausmetodit eriteltynä varsinaisista testeistä.

* regex.io-kansio sisältää rajapinnan IO ja luokan ConsoleIO, jotka luotiin alunperin testauksen helpottamiseksi. Näitä käytetään kuitenkin edelleen tekstipohjaisessa UI-luokassa, jotta riippuvuuksien injektointi olisi helpompaa.

* regex.logic-kansio sisältää itse toiminnallisuuden. Validator-luokka hoitaa syötetyn säännöllisen lauseen tarkastuksen syntaksivirheiden osalta, jonka jälkeen hyväksytty regex voidaan tulkata. Tämä tapahtuu Regex-luokassa, joka sisältää kaikki tulkin toiminnalliset osat.

* regex.ui-kansio sisältää kaksi vaihtoehtoista käyttöliittymää: tekstipohjaisen UI-luokan ja vakiona käytössä olevan graafisen GUI-luokan.

* lisäksi omassa testikansiossaan on yksikkötestiluokat TestRegex ja TestValidator.

## Toiminta

Ohjelma toimii pitkälti [määrittelydokumentissa](/dokumentaatio/maarittelydoc.md) kuvatulla tavalla, eli säännöllistä lauseketta läpikäymällä se luo rekursiivisesti erilaisia merkkijonopolkuja, ja testaa, johtaako joku näistä haluttuun merkkijonoon. Hauska erikoisuus on se, että säännöllistä lauseketta luetaan oikealta vasemmalle, "väärin päin". Tämä johtuu pitkälti omasta kokeilunhalustani, sillä halusin selvittää, voiko algoritmin toteuttaa näin.

### Aika- ja tilavaativuusanalyysi
* Aikavaativuus vaikuttaa olevan O(n<sup>3</sup>), kuten [testausdokumentissa](/dokumentaatio/testausdoc.md) osoitetaan. Todellisuudessa lausekekohtainen aikavaativuus on kuitenkin jotain O(n) ja O(n<sup>3</sup>) väliltä, sillä tietyt erikoismerkit (tai niiden puute) muuttavat tätä ylärajaa. Käytännön tilanteissa käytetyt regexit ovat kuitenkin niin lyhyitä, että pisimmätkin ohjelma suorittaa ilman loppukäyttäjälle näkyvää viivettä.  
* Tilavaativuus on ilmeisesti hieman isompi kuin alunperin ajateltu O(m). Tämä johtuu siitä, että tietyillä erikoismerkeillä sallitaan rekursion jatkuminen ilman merkin lisäystä (mm. Kleenen tähti), jolloin myös säännöllisen lausekkeen pituus vaikuttaa muistissa pidettävien tilojen määrään. Yläraja on siis O(nm), jossa n on regexin ja m vertailtavan merkkijonon pituus. Todellisuudessa tämä on kuitenkin lähes aina ~O(m), sillä tyhjiä rekursioita ei esiinny useinkaan kovin montaa.

## Puutteet ja parannukset

* Selkeästi ohjelmaa voisi vielä optimoida hyvin suurille syötteille, ja säätää niin, että tilavaativuus olisi aina lineaarinen. Tämä vaatisi kuitenkin suuria rakenteellisia muutoksia ja/tai taitavia oivalluksia.
* Ohjelmasta puuttuu lukemattoman paljon nykyisten modernien regex-ohjelmien käyttämiä erikoistunnisteita,
mutta nämä eivät monestikkaan teoreettisesti laajenna tunnistettujen kielten joukkoa, vaan helpottavat ohjelmoijaa.
* Yleisesti käytetty takaisinviittaus laajentaisi tulkkia yli säännöllisten kielten, ja voisi olla selkeä parannusehdotus tulevaisuuteen, mikäli
projektia haluaisi jatkaa kurssin jälkeenkin. Tämä toteutus perinteisesti kuitenkin vaatisi logiikan kääntämistä niin, että lukemista tehtäisiin vasemmalta oikealle (tai ominaisuuden käyttöä hieman eri tavalla, joka voisi myös olla kiinnostavaa).

## Lähteet

Kaikki lähteet on mainittu [määrittelydokumentissa](/dokumentaatio/maarittelydoc.md), sillä perusteorian jälkeen järkeviä ei-automaattipohjaisia toteutuslähteitä ei enää löytynyt lisää.