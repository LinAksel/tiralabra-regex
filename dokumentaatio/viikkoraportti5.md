# Viikko 5

### Päiväkirja/muistiinpanot

#### Tiistai

Aliarvioin hieman käyttöliittymän luomiseen tarvittavan ajan, ja aikaa paloi päivästä
suhteellisen paljon GUI:n luomiseen. Samalla kuitenkin löytyi lisää bugeja, joten lopputuloksena on uusi käyttöliittymä ja hieman paremmin toimiva tulkki!

### Torstai

Lähinnä ongelmien etsintää manuaalisesti ja automaattisilla testeillä, löysin ja korjasin muutaman, ja GUI sai hieman parantelua.
Tyhjä merkki myös lisätty regexiin, se on nyt \\e. Tämä vaikuttaa toimivan suhteellisen hyvin, mutta kattavampi testaus jää ensi viikolle, kun perustavammat bugit on nitistetty!

### Perjantai

Vertaisarviossa todella hyvää palautetta muun muassa koodin sisäisestä laadusta, onneksi kurssia on vielä jäljellä.
Arvioija oli myös löytänyt hyvin perustavanlaatuisen bugin .*-yhdistelmän käytöstä! Pohdin tätä vähän aikaa, ja sain ilmeisesti ongelman korjattua, ja laajensin testausta.
Alkaa tuntua siltä, että sille, miksi en löytänyt juurikaan lähteitä tälle lähestymistavalla regex-tulkkiin on syy, sillä sotkuisia ehtoja ja edge caseja vain kasautuu.
En kuitenkaan kadu sitä, että valitsin tulkin, sillä projekti on opettanut itseäni tähän mennessä paljon.

## Tehdyt työt ja edistyminen

Tällä viikolla tein pitkälti sitä mitä odotinkin tekeväni: koetin rikkoa ohjelmaa, ja korjata löytyneitä bugeja. Visuaalinen käyttöliittymä auttoi tässä paljon,
ja lopputuloksena on nyt paljon paremmin toimiva tulkki! Myös mainittu tyhjä merkki on nyt lisätty, joskin sen testaaminen jää tulevalle viikolle, sillä \\-merkin yleisemmät ongelmat veivät tällä viikolla paljon aikaa.

## Epäselvyyksiä

Jälleen kerran suunta vaikuttaa mielestäni suhteellisen selkeältä, uhkaavasti paisunutta koodia
olisi taas syytä koettaa siistiä as-is, eli ottaa aikaa bugien etsimiseltä ja siistiä olemassaoleva koodi luettavammaksi ja laajennettavammaksi.
Ainut pohdituttava asia on suorituskykytestauksen järkevä tekeminen: onko lähestymistapani, jossa siis koetetaan hieman tarkoitushakuisesti etsiä mahdollisia ohjelman kompastuskiviä (esim kleenen tähti+sulkeet niin että paljon rekursiohaaroja), ja
testata näin erilaisten syötteiden ja regexien toimintaa?

## Mitä opin ja mitä teen seuraavaksi

Tällä viikolla todella huomasi, että testien tekemisellä on syynsä! Yrittäessäni korjata bugeja rikoin monta kertaa vahingossa ensin jo
toimivia ominaisuuksia, jotka onneksi huomasin nopeasti valmiiden testien avulla. Viikon oppi siis oli, että testaus ei koskaan ole turhaa.
Seuraavaksi alkaa loppukiri, eli siistimistä, dokumentointia ja ennen kaikkea suorituskykytestausta järkeväksi. Myös stack overflow on pakollinen riesa kaikissa rekursio-ohjelmissa, mutta koetan tutkia tapoja, joilla tämä ei olisi niin ilmeistä.
On myös pikkuhiljaa hyväksyttävä, että tämä on täydellinen esimerkki ohjelmasta, joka ei ole koskaan valmis: aina voisi lisätä, korjata tai varmistaa enemmän.
