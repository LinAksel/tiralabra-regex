## Yksikkötestaus

Tällä hetkellä yksikkötestaus kattaa eri operaattorien yksinkertaisen toiminnan,
ja muutaman monimutkaisen yhdistelmän, mutta testausta on tarkoitus lisätä
monimutkaisten lauseiden osin. Testit on tällä hetkellä helppo ajaa komennolla

```
mvn test
```

Jacoco-raportin voi luoda omalle koneelleen komennolla

```
mvn test jacoco:report
```

## Suorityskykytestaus

Suorituskykytestausta rakennetaan luokkaan Performance, ja niiden käynnistämisestä
päivitetää lisätietoa tähän dokumenttiin. Tällä hetkellä käyttö on mahdollista vain Main-luokan kautta.

### Testituloksia

Yksikkötestauksen perusteella kaikkien ominaisuuksien tulisi toimia tällä hetkellä oikein,
ja erilaisia yhdistelmiä lisätään koko ajan testeihin. Suorituskykytestauksen alustavat tulokset näyttävät siltä,
että suoritusaika kasvaa lineaarisesti säännöllisen lausekkeen pituuden suhteen, mutta eri vakiokertoimilla. Tästä vielä kaavio ja lisätestausta ennen loppupalautusta!
