# PokingScanBot #

## Kuvaus ##
  Robotin tarkoituksena on mitata esineiden pintaa ja piirtää niistä graafinen kuva. Robotissa on kaksi osaa, kääntöpöytä jossa tutkittavaa esinettä käännetään ja mittausosa jonka kosketus tai ultraäänisensorilla mitataan esineen pinnan etäisyyttä. 

Ultraäänisensorin toiminta on kohtuullista jos esine on pyöreä ja tasainen, siis sellainen, että kaiku palaa sensoriin oikein. Epätasaisilla kappaleilla toiminta on erittäin epävarmaa.

Kosketussensori on erittäin epäluotettava: kosketussensori ei ole tarpeeksi herkkä, vaan sen pyörä jää helposti sutimaan eikä saa tulosta.

Saadut tulokset esitetään PC:llä ohjelmalla PokingScanBotVisualizer johon tulokset siirtyvät robotista Bluetooth-yhteydellä.

## Rakenne ##

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_105910.jpg)

Robotin tärkeimmät osat ovat kaksi moottoria, kosketussensori ja ultraäänisensori.

----------


**Kääntöpöytä**

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110048.jpg)
Kääntöpöydän yleisrakenne. Jalat pitävät pöydän jokseenkin suorassa, poikittain  oleva vaalea palkki on tarkoitettu teipattavaksi alustaan estämään pöydän liikkumista varsinkin kosketussensoria käyttäessä. Pahvilevy on tutkittavan esineen alustana, ja sen läpi tulevat nastat tukevat levyn lisäksi myös tutkittavaa esinettä. Pahvi on sopivan karkeaa, etteivät pöydän kääntyessä sillä olevat esineet liiku herkästi.

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110056.jpg)
Kääntöpöytä ilman pahvia. Palkit joiden päälle pahvilevy asetetaan on kiinnitetty siten, että niitä voi hiemaan käännellä jos nastat muuten sattuvat huonoihin paikkoihin, esim. niin että tutkittava esine menee vinoon. Kääntöpöydän kääntämiseen käytetään kierukka-akselia. Kierukka-akselilla on erittäin korkea välityssuhde, joten kääntöpöytä liikkuu hitaasti ja tarkasti.

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110138.jpg)

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110154.jpg)
Kääntöpöydän alapuoli. Pohjan akselit tukevoittavat pöytää ja pitävät kierukka-akselin tarpeeksi kireänä.

----------


**Mittausosa**

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110111.jpg)
Legosetissäni ei ollut sopivaa hammastankoa, joten mittausosan moottori siirtää vapaasi liukuvaa kiskoa ja siihen kiinnitettyjä sensoreita renkaan avulla. Kiskossa ja rungossa olevat stoppari estävät kiskon irtoamisen robotista. Takaosan vaaleat palkit tukevoittavat ja estävät kiskon liikkumisen sivulle kun kosketussensori osuu kohteeseen. Molempien sensorien kiinnitys kiskoon lisää sen painoa ja auttaa renkaan pitävyydessä. Kisko ei liiku äänisensoria käyttäessä.

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/20150108_110040.jpg)
Moottorin alla oleviin harmaaseen ja valkoiseen palkkiin voidaan kiinnittää teippiä turhan liikkumisen etämiseksi. Moottorin taka olevaan harmaaseen palkkiin täytyy kiinnittää teippiä, muuten mittausosa kaatuu nokalleen erittäin helposti, varsinkin jos kosketussensoria käytetään ja kisko liikkuu eteenpäin.

----------


## Koodin rakenne ##

Robotin koodi koostuu kahdesta ohjelmasta: robotille ladattavasta PokingScanBot-ohjelmasta ja PC:llä käytettävästä PokingScanBotVisualizer-ohjelmasta.

----------


**PokingScanBot**

Ohjelman luokat ja pakkaukset:

**logging:**

- DistanceLogger.java: Tallentaa mittaustuloksia tiedostoon. Ei käytössä tässä versiossa, vaan tiedot siirretään bluetooth-yhteydellä.

- PcConnection.java: Muodostaa Bluetooth-yhteyden tietokoneen ja robotin välille.

**main**

- Main.java: Käynnistää yhteyden ja mittaukset.

**movement**

- MovementController.java: hallitsee mittauksia ja moottoreita.
- PokerMotor.java: hallitsee kiskoa liikuttavaa moottoria ja arvioi kiskon sijaintia. Rengasta pyöritetään kunnes kosketussensori havaitsee osuman, sen hetkinen kierroslukumäärä talletetaan ja lähetetään tuloksena ja moottoria ajetaan takaisinpäin sama määrä jotta kisko palaa alkuasentoonsa.
- TurnTableMotor.java: hallitsee kääntöpöydän moottoria.

**sensors**

- Toucher.java: käyttää kosketussensoria. Käytännössä tämä luokka on tehty korjaamaan ongelmaa, jossa kosketussensorin käyttö suoraan aiheutti hämmentäviä ongelmia muiden moottoreiden toiminnassa.


----------


**PokingScanBotVisualizer**

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/visualisointi.jpg)

Ohjelman luokat ja pakkaukset:

**connections**
- BTConnection.java: Muodostaa yhteyden robotin ja tietokoneen välillä.

**main**
- Engine.java: hoitaa visualisointiohjelman ruudunpäivitystä ja noutaa tietoja robotilta ResultListin avulla.
- Main.java Käynnistää yhteydet ja visualisointiin liittyvät ikkunat sekä Enginen.

**results**
- ResultList.java: Tallettaa ja noutaa robotilta mittaustuloksia robotilta.

**visualization**
- AngleCalculator.java: Laskee mittaustuloksille etäisyyden, kulman ja alkupisteen perusteella oikean paikan ympyrän kehällä. Robotilta saatava tulos on pelkkä etäisyys, jolle tässä lasketaan annetun mittaustarkkuuden perusteella oikea kulma.
- SettingsWindow.java: Mittausasetustan hallintaan käytettävä ikkuna ja sen komponentit.
- VisualizationGraphics.java: Visualisointi-ikkunan graafiset osat.
- VisualizationWindow.java: Visualisoint-ikkunan luonti.

Esimerkki ohjelman toiminnasta kooditasolla:

1. Robotti käynnistetään ja se jää odottamaan PcConnectionin syntymistä.
2. Tietokoneella oleva visualisointiohjelma käynnistetään ja se etsii yhteyttä.
3. Yhteys muodostuu, robotti siirtyy odottamaan käskyä.
4. Visualisointiohjelmasta valitaan haluttu tarkkuus ja mittaustapa jotka lähetetään robotille.
5. Robotti vastaanottaa käskyn ja siirtyy mittamaan halutulla sensorilla.
6. Robotin MovementController liikuttaa kääntöpöytää ja tekee mittauksia jotka se lähettää ja tallentaa PcConnectionin ulosmeneviin viesteihin.
7. Visualisointohjelma tarkistaa oman BTConnectionin input-streamia sekunnin välein, ja jos se löytää uuden viestin se talletetaan ResultListiin.
8. ResultListin tulokset piirretään oikeille paikoilleen AngleCalculatorin avulla.

## Testaus ##

Alunperin ohjelman tarkoitus oli käyttää ainoastaan kosketussensoria. Kun olin saanut mittausosan kiskon toimintavalmiiksi tajusin, ettei kosketussensori ole riittävän herkkä. Koska jouduin käyttämään kiskon liikutteluun rengasta enkä hammastankoa, ei kitka yksinkertaisesti riitä painamaan kosketussensoria tarpeeksi syvälle vaan rengas jää pyörimään ikuisesti. Sensoria täytyi usein hieman auttaa sormella jotta kosketus oikeasti saatiin havaittua. Kosketussensori onkin jäänyt lopullisessa versiossa lähinnä hauskaksi lisäominaisuudeksi, joka ei tuota järkeviä tuloksia.

Tämän jälkeen aloin keskittymään lähes täysin ultraäänisensoriin, jolla tulokset olivat paljon rohkaisevampia. Käytin tämän sivun ensimmäisen kuvan kaltaista tölkkiä testikappaleena, jonka uskoin olevan mahdollisimman helppo. Pyöreästä tölkistä kaiku lähtee oikeaan suuntaan ja metalli heijastaa ääntä hyvin. Sainkin melko hyviä tuloksia:

![](https://raw.githubusercontent.com/Ecxo/massive-ironman/master/docs/kuvat/tulos.jpg)

Kokeilin myös muita muotoja, mutta niissä tulokset olivat pettymys. Suorakaiteen muotoisesta laatikosta saa kyllä tuloksen, mutta sen voi tulkita suorakaiteeksi vain mielikuvituksella ja hyvällä tahdolla. Uskon että kehittyneempien sensoreiden avulla tulokset saattasivat olla parempia.

## Rajoitukset ja tulevaisuus ##
Alkuperäisessä ideassani robotista oltaisiin otettu mittauksia kolmella aksellilla, eikä vain kahella kuten nyt. Tulevaisuudessa lisäisinkin mahdollisuuden nostaa ja laskea mittausosaa tai kääntöpöytää, jotta esineen pinnasta saataisiin yksinkertainen kolmiulotteinen malli.

Kehittyneempien legojen lasersensori olisi mielenkiintoinen kokeilu, nyt huonosti  heijastavat esineet ovat erittäin huonoja mallintaa. Ei-ympyränmuotoisten esineiden satunnaisen näköisiä kuvioita voisi ehkä parantaa jonkinlaisella algoritmilla, joka tasoittaa mittaustuloksien heittelyä vierekkäisissä mittauksissa.

Kosketussensorista tuli käytännössä hyödytön ominaisuus sensorin jäykkyyden ja kiskon liikutuksen rajoitteiden takia. Kiskoon ei saada tarpeeksi kitkaa eikä se myöskään ole tarpeeksi tukeva. Yritin tehdä kiskosta tukevamman, mutta se vain lisäsi erilaisten kiinnityspalojon takertumista toisiinsa, joten kisko jäi nykyiseen huterahkoon malliinsa. 

Visualisointiohjelma voisi olla esteettisempi, mutta minusta esimerkiksi tölkeistä tulevat kuvat ovat oikeastaan aika kauniita.

## Käyttöohje ##

1. Käynnistä robotti ja käynnistä ohjelma. Robotti jää odottamaan Bluetooth-yhteyttä.
2. Käynnistä tietokoneen ohjelma. Yhteys muodostuu ja näytölle tulee visualisointi-ikkuna ja asetusikkuna. Valitse asetusikkunasta mittauksen tarkkuus (eli kuinka monta kertaa esineen käännön aikana pysähdytään ja otetaan mittaus) ja mittaustapa. Sonic käynnistää mittauksen äänisensorilla ja Touch kosketussensorilla.
3. Robotti alkaa tekemään mittauksia. Tulokset näkyvät punaisina pisteinä ja koordinaatiston keskipiste vihreänä pisteenä.













