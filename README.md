> EÖTVÖS LORÁND TUDOMÁNYEGYETEM
>
> Informatika Kar
>
> ***Rubik Kocka***

**Témavezető: Szerző:**

Dr. Gregorics Tibor Gál Gergely

Budapest, 2023

# Tartalom

[**Köszönetnyilvánítás**
[4](#köszönetnyilvánítás)](#köszönetnyilvánítás)

[**1.** **Témabejelentő** [5](#témabejelentő)](#témabejelentő)

[**2.** **Bevezetés** [6](#bevezetés)](#bevezetés)

[**3.** **Felhasználói dokumentáció**
[7](#felhasználói-dokumentáció)](#felhasználói-dokumentáció)

[3.1. Az alkalmazásról [7](#_Toc134374829)](#_Toc134374829)

[3.2. Az alkalmazás futtatása
[7](#az-alkalmazás-futtatása)](#az-alkalmazás-futtatása)

[3.3. Az alkalmazás használata
[9](#az-alkalmazás-használata)](#az-alkalmazás-használata)

[3.3.1. Menü [9](#menü)](#menü)

[3.3.2. Az „Algorithm Data” menü
[10](#az-algorithm-data-menü)](#az-algorithm-data-menü)

[3.3.3. A Settings menü [12](#a-settings-menü)](#a-settings-menü)

[3.3.4. A Játék [13](#a-játék)](#a-játék)

[**4.** **Fejlesztői dokumentáció**
[15](#fejlesztői-dokumentáció)](#fejlesztői-dokumentáció)

[4.1. Elemzés [15](#elemzés)](#elemzés)

[4.1.1. Feladat leírás [15](#feladat-leírás)](#feladat-leírás)

[4.1.2. Funkcionális leírás
[16](#funkcionális-leírás)](#funkcionális-leírás)

[4.1.2.1. User Story táblázat
[17](#user-story-táblázat)](#user-story-táblázat)

[4.1.2.2. Use case diagram [18](#use-case-diagram)](#use-case-diagram)

[4.1.3. Nem-funkcionális leírás
[19](#nem-funkcionális-leírás)](#nem-funkcionális-leírás)

[4.2. Tervezés [20](#tervezés)](#tervezés)

[4.2.1. Szoftver architektúra
[20](#szoftver-architektúra)](#szoftver-architektúra)

[4.2.2. Modell és nézet [21](#modell-és-nézet)](#modell-és-nézet)

[4.2.2.1. Osztály diagram [21](#osztály-diagram)](#osztály-diagram)

[4.2.2.2. Osztályok és főbb metódusaik
[22](#osztályok-és-főbb-metódusaik)](#osztályok-és-főbb-metódusaik)

[4.2.3. Adatbázis [24](#adatbázis)](#adatbázis)

[4.2.3.1. algorithms.JSON [24](#algorithms.json)](#algorithms.json)

[4.2.3.2. pochmanTargetPositions.JSON
[24](#pochmantargetpositions.json)](#pochmantargetpositions.json)

[4.2.3.3. settings.JSON [25](#settings.json)](#settings.json)

[4.2.4. Speciális elemek [26](#speciális-elemek)](#speciális-elemek)

[4.3. Megvalósítás [26](#megvalósítás)](#megvalósítás)

[4.3.1. Felhasznált technológiák
[26](#felhasznált-technológiák)](#felhasznált-technológiák)

[4.3.2. Esetleges eltérések a tervtől
[27](#esetleges-eltérések-a-tervtől)](#esetleges-eltérések-a-tervtől)

[4.4. Tesztelés [28](#tesztelés)](#tesztelés)

[4.4.1. Egység tesztek [28](#egység-tesztek)](#egység-tesztek)

[4.4.2. Végfelhasználói tesztesetek
[29](#végfelhasználói-tesztesetek)](#végfelhasználói-tesztesetek)

[**5.** **Továbbfejlesztési lehetőségek**
[30](#továbbfejlesztési-lehetőségek)](#továbbfejlesztési-lehetőségek)

[**6.** **Összefoglalás** [31](#összefoglalás)](#összefoglalás)

[**7.** **Irodalomjegyzék** [32](#irodalomjegyzék)](#irodalomjegyzék)

# 

# **Köszönetnyilvánítás**

Ezúton szeretnék köszönetet mondani témavezetőmnek, Gregorics Tibornak,
aki szakértelmével, hasznos magyarázataival és a konzultációk során
biztosított elengedhetetlen tanácsaival hatalmas segítséget nyújtott
szakdolgozatom elkészüléséhez.

Hálával tartozom továbbá szüleimnek, akik nélkül ez a szakdolgozat nem
jöhetett volna létre. Köszönöm nekik, hogy tanulmányaim során türelemmel
és megértéssel támogattak, és minden helyzetben mellettem álltak.

Köszönöm mindenkinek!

# **Témabejelentő**

A Rubik Kocka kirakása már évtizedek óta foglalkoztatja embereket. A
színek megfelelő helyre illesztése egy nagyon bonyolult probléma. A
feladatra már rengeteg megoldás született, mindegyik algoritmus más és
más lépéskombinációkkal oldja meg a problémát. Vannak olyan
algoritmusok, amelyikkel az emberek számára gyorsan kirakható a kocka.
Van amelyik hosszú számolás után a legkevesebb lépést próbálja elérni,
ez maximum 20 lépés. Tervem szerint az általam használt algoritmus azt
szolgálja, hogy az emberek “vakon” tudják kirakni ezt a logikai játékot.

Az alap algoritmus, amit megtanulhatunk a kockával az Old Pochmann
metódus lesz, mely mindössze 3 permutációt használ, a J-t, az Y-t és a
T-t. Ebben az algoritmusban elválasztjuk az éleket a sarkoktól és ezeket
két külön álló egységként kezelve oldjuk meg az elemek
helyreillesztését. A játék hagyományos megoldásában ilyenkor bekötik a
játékos szemét, és ez után kell kiraknia a kockát. Az én alkalmazásom
ezt fogja imitálni, és megtanítja a felhasználót a vakon kirakás
módszerére. A kockát a játék megkezdése előtt tetszőlegesen
összekeverhetik, vagy más algoritmusok megtanulására is használhatják.
Az alkalmazáshoz saját algoritmusokat (lépéssorozatokat) lehet
hozzáadni, és ezeknek a memorizálásában is tud segíteni a program.

A program Java alapú Processing 4 -ben íródik. A Processing egy egyszerű
programozási környezet, amelyet azért hoztak létre, hogy megkönnyítse a
vizuálisan orientált alkalmazások fejlesztését, különös tekintettel az
animációra, és azonnali visszajelzést adjon a felhasználóknak az
interakción keresztül.

# **Bevezetés**

Egészen fiatal koromból rengeteg időt szántam a Rubik-kocka és annak
különböző méretű és változatú kockával való játékra, azok kirakására, új
és új kirakási módok kitalálására. Gyakorlatilag minden szabadidőmben
ezzel foglalkoztam. Legnagyobb örömömre sikerült rengeteg olyan embert
megismernem, akik ezzel foglalkoznak, és tanítják a világot, önmagukat,
és mindenkit, aki nyitott erre. Szeretném ebből a rengeteg emberből
kiemelni Török Ágostont, aki a magyar Rubik-kockás palettán, rengeteg
internetes tartalommal segít az érdeklődőknek a fejlődésben.

Ezzel a projekttel a célom, hogy akár a teljesen kezdők, akár a
Rubik-kockát már kirakni képes "játékosok", megtanulhassák hogyan
működik a vakon kirakás ágazat a Rubik-kockázás világán belül. Ez a
program azért volt számomra érdekes, mert valójában sosem sikerült
megtanulnom ezt a megoldást. Tehát laikus szemével de egy tapasztalt
kockázó látásmódjával és lelkesedésével állhattam neki a munkának.

3.  # **Felhasználói dokumentáció**

    1.  <span id="_Toc134374829" class="anchor"></span>Az alkalmazásról

Az alkalmazás célja, hogy annak felhasználója elsajátíthassa a
Rubik-kocka vakon kirakásának módszerét, saját, megtanulásra váró
algoritmusait összegyűjthesse, gyakorolhassa, továbbá megtekinthesse,
hogy egy gép hogyan oldja meg ezt a feladatot. Ebben a fejezetben a
felhasználó megismerkedhet az alkalmazás felületével, funkcióival és
azok használatával, valamint azok hasznos alkalmazásával a Rubik-kocka
vakon kirakásának elsajátításához.

## Az alkalmazás futtatása

<img src="media/image2.png" style="width:6.3in;height:1.81181in" /> Az
alkalmazás egy Java formátumban megvalósított program, melynek
használati útmutatója, billentyűzet beállítások, letöltési útmutatók és
még sok egyéb hasznos információ megtalálható a
<https://people.inf.elte.hu/gt8yb1/> weboldalon. Az oldal alján
található egy letöltés gomb, amely egy Java futtatható állományt és a
további szükséges fájlokat tartalmazza. Amennyiben a felhasználó
rendelkezik a megfelelő Java verzióval, nincs szükség további lépésekre
a program használatához. A felhasználók könnyedén hozzáférhetnek a
szükséges erőforrásokhoz és a program funkcionalitásához az említett
weboldal segítségével.

<img src="media/image3.png" style="width:6.30208in;height:7.88542in" />

2\. ábra a teljes weboldalról

3.  ##  Az alkalmazás használata

    1.  ### Menü

<img src="media/image4.png" style="width:6.3in;height:3.96181in" />A
főmenü tartalmazza a "Play" gombot, amely az automatikus megoldásért és
a lépésről lépésre történő megoldásért felelős. Az "Algorithm Data"
módot, amelyben a felhasználó saját algoritmusait kezelheti. A
"Beállítások" menüpontot, amely a beállítások és azok mentéséért
felelős. Az "Info" gombot, amely az előbbiekben ismertetett
<https://people.inf.elte.hu/gt8yb1/> weboldalra vezet, valamint a
"Kilépés" gombot, amely segítségével a felhasználó bezárhatja az
alkalmazást.

### Az „Algorithm Data” menü

<img src="media/image5.png" style="width:4.98958in;height:3.01042in" /><img src="media/image6.png" style="width:5.03194in;height:2.97917in" />
Itt található a 2 az algoritmusok tárolásáért felelős gomb, és a
gyakorlás funkció.

<img src="media/image7.png" style="width:5.04236in;height:3.01042in" /><img src="media/image8.png" style="width:5.02153in;height:3.00042in" />

### A Settings menü

Ezen a menün keresztül lehet beállítani a kocka színeit, a face id-k
megjelenését, továbbá azt, hogy a program megálljon-e a különböző
lépések demonstrációja között. Ezeket az adatokat a program elmenti, és
a következő indításkor már az új beállítások lesznek az
alapértelmezettek.<img src="media/image9.png" style="width:6.08264in;height:4in" />

### A Játék

<img src="media/image10.png" style="width:6.21962in;height:6.53216in" />A
„Play” gomb megnyomásakor 2 ablak nyílik meg. Az elsö a rubik kockát
tartalmazza, a másikon pedig az utasítások és a lépések jelennek meg.

<img src="media/image11.png" style="width:6.01042in;height:4.03125in" />

4.  # **Fejlesztői dokumentáció**

    1.  # Elemzés

        1.  # Feladat leírás

A fejlesztendő alkalmazás célja, hogy a felhasználók megtanulják a
Rubik-kocka vakon történő megoldását az Old Pochmann módszerrel. Az
alkalmazás fő célja nem a leggyorsabb vagy legoptimálisabb megoldás
megtalálása, hanem a felhasználók oktatása a módszer elsajátításában.

A projekt részletei a következők:

1.  Az alkalmazásnak biztosítania kell egy intuitív és könnyen
    használható felületet a Rubik-kocka vakon történő megoldásának
    tanulására az Old Pochmann módszerrel.

2.  Az alkalmazásnak lehetővé kell tennie a felhasználók számára, hogy
    saját lépéssorozatokat, úgynevezett "algoritmusokat" adjanak hozzá,
    melyek segítségével saját technikákat is kifejleszthetnek a kocka
    megoldására.

3.  A felhasználóknak lehetőségük kell legyen az algoritmusok mentésére,
    törlésére és módosítására.

4.  Az alkalmazásnak tartalmaznia kell egy olyan felületet, ahol a
    felhasználók gyakorolhatják a megtanult algoritmusokat, és
    fokozatosan fejleszthetik a Rubik-kocka megoldására vonatkozó
    képességeiket.

5.  A kocka körül forgatható, és gombnyomással „hajtható” kell legyen.

6.  A fejlesztendő alkalmazásnak kompatibilisnek kell Windows 10
    rendszerrel.

    1.  # Funkcionális leírás

<!-- -->

1.  Az alkalmazásfejlesztés először egy Processing “sketch”
    elkészítésével kezdődjön.

2.  A sketch-et a kocka elkészültekor fordítsuk Java alkalmazássá.

3.  Ezután ezt az egységesen generált kód kerüljön feldarabolásra és
    Java platformon működjön.

4.  Az alkalmazás grafikus felhasználói felületet (GUI) használjon a
    könnyebb kezelhetőség érdekében, például JavaFX vagy Swing
    technológiával.

5.  Az alkalmazásban jelenjen meg egy 3D-s Rubik-kocka model, amellyel a
    felhasználók interaktívan gyakorolhatják a megoldást.

6.  Az alkalmazásnak támogatnia kell az Old Pochmann módszer alapján
    történő vakon megoldást, és ehhez lépésről lépésre útmutatást kell
    nyújtania.

7.  Az alkalmazásban legyen lehetőség saját algoritmusok hozzáadására,
    mentésére, törlésére és módosítására.

8.  Az alkalmazásban legyen lehetőség a felhasználók számára, hogy a
    megtanult algoritmusokat gyakorolhassák, és visszajelzéseket
    kapjanak a sikeres vagy sikertelen próbálkozásról.

9.  Az alkalmazásnak tartalmaznia kell egy súgó vagy útmutató részt,
    ahol a felhasználók segítséget kaphatnak az alkalmazás használatával
    kapcsolatban.

    1.  # User Story táblázat

| ID  | Felhasználói eset             | Leírás |                                            | Prioritás | Státusz                                                            |
|-----|-------------------------------|--------|--------------------------------------------|-----------|--------------------------------------------------------------------|
| 1   | Alkalmazás indítása           | GIVEN: | Az alkalmazás telepítve van                | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | Alkalmazás indítása                                                |
|     |                               | THEN:  |                                            |           | A menü megjelenik                                                  |
| 2   | Kilépés                       | GIVEN: | Menü felület látható                       | Alacsony  | Kész                                                               |
|     |                               | WHEN:  |                                            |           | Kilépési szándék                                                   |
|     |                               | THEN:  |                                            |           | Alkalmazás bezárása                                                |
| 3   | Algoritmus tanulás menü       | GIVEN: | Menü felület látható                       | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | „Algorithm Data” menüpont választása                               |
|     |                               | THEN:  |                                            |           | Az algoritmus tanulás menü megjelenik                              |
| 4   | Meglevő algoritmus tanulása   | GIVEN: | Algoritmus tanulási menü látható           | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | A „Learn Algorithm” menüpot választása                             |
|     |                               | THEN:  |                                            |           | A játék elindul a választott algoritmussal                         |
| 5   | Új algoritmus tanulása        | GIVEN: | Algoritmus tanulási menü látható           | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | „Add Algorithm” menüpont választása                                |
|     |                               | THEN:  |                                            |           | A felhasználó megadhatja az általa választott algoritmus adatait   |
| 6   | Algoritmus törlése            | GIVEN: | Algoritmus tanulási menü látható           | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | „Delete Algorithm” menüpont választása                             |
|     |                               | THEN:  |                                            |           | Az algoritmus törlődik                                             |
| 7   | Önmegoldó mód                 | GIVEN: | Menü felület látható                       | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | Play menüpont választása                                           |
|     |                               | THEN:  |                                            |           | A játék elindul a megadott beállításokkal                          |
| 8   | Beállítások menü              | GIVEN: | Menü felület látható                       | Közepes   | Kész                                                               |
|     |                               | WHEN:  |                                            |           | „Settings” menüpont választása                                     |
|     |                               | THEN:  |                                            |           | A Beállítások menü megjelenik                                      |
| 9   | Infó menüpont                 | GIVEN: | Menü felület látható                       | Alacsony  | Kész                                                               |
|     |                               | WHEN:  |                                            |           | „Info” menüpont választása                                         |
|     |                               | THEN:  |                                            |           | Az információs weboldal megjelenik                                 |
| 10  | Kocka fordítása               | GIVEN: | A játék fut                                | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | Valamelyik fordítási input (billentyű kombináció) lenyomásra kerül |
|     |                               | THEN:  |                                            |           | A kocka elfordul                                                   |
| 11  | Kocka összekeverése           | GIVEN: | A játék fut, és a kirakás nem kezdődött el | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | A keverés input (billentyű kombináció) lenyomásra kerül            |
|     |                               | THEN:  |                                            |           | A kocka 10-20 lépés alatt összekeveri magát                        |
| 12  | Sikeres algoritmus kirakás    | GIVEN: | A játék fut, algoritmus tanulás módban     | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | A felhasználó sikeresen kirakja az algoritmust                     |
|     |                               | THEN:  |                                            |           | Tetszőleges mozgatással a kocka visszaáll alaphelyzetbe            |
| 13  | Sikertelen algoritmus kirakás | GIVEN: | A játék fut, algoritmus tanulás módban     | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | A felhasználó elrontja az algoritmust                              |
|     |                               | THEN:  |                                            |           | A kocka visszaáll alaphelyzetbe                                    |
| 14  | Kocka automatikus kirakása    | GIVEN: | A játék fut                                | Magas     | Kész                                                               |
|     |                               | WHEN:  |                                            |           | A kirakás input (billentyű kombináció) lenyomásra kerül            |
|     |                               | THEN:  |                                            |           | A kocka nekiáll kirakni magát                                      |
| 15  | Szabad játék                  | GIVEN: | A program nem kezdte el a kirakást         | Alacsony  | Kész                                                               |
|     |                               | WHEN:  |                                            |           | Valamelyik fordítási input (billentyű kombináció) lenyomásra kerül |
|     |                               | THEN:  |                                            |           | A kocka szabadon kirakható, forgatható                             |

# <img src="media/image12.png" style="width:8.48889in;height:3.71597in" />Use case diagram

# Nem-funkcionális leírás

1.  Teljesítmény: Az alkalmazásnak gyorsan és zökkenőmentesen kell
    működnie, a felhasználói interakciókra gyors válaszidővel reagálva.

2.  Felhasználóbarát: Az alkalmazásnak könnyen használhatónak és
    érthetőnek kell lennie a felhasználók számára, függetlenül attól,
    hogy mennyi tapasztalattal rendelkeznek a Rubik-kocka megoldásában.
    Viszont arra támaszkodhat, hogy a felhasználó tanulmányozta az
    alkalmazás leírását a weboldalon.

3.  Kompatibilitás: Az alkalmazásnak kompatibilisnek kell lennie Windows
    10 operációs rendszerrel.

4.  Megbízhatóság: Az alkalmazásnak hibamentesen kell működnie, és
    kezelnie kell a váratlan helyzeteket vagy felhasználói hibákat
    anélkül, hogy összeomlana vagy adatvesztést okozna.

5.  Karbantarthatóság: Az alkalmazásnak jól strukturált és dokumentált
    kóddal kell rendelkeznie, hogy az könnyen karbantartható és
    bővíthető legyen.

6.  Tesztelhetőség: Az alkalmazásnak lehetővé kell tennie a könnyű
    tesztelést és hibakeresést, valamint támogatnia kell az automatizált
    tesztelési megoldásokat.

7.  Rugalmasság: Az alkalmazásnak képesnek kell lennie arra, hogy új
    funkciók és módosítások könnyen integrálhatók legyenek anélkül, hogy
    a meglévő funkcionalitást befolyásolnák.

8.  Átadhatóság: Az alkalmazásnak úgy kell lennie megtervezve és
    implementálva, hogy könnyen átadhassuk azt más fejlesztőknek vagy
    csapatoknak a további fejlesztés és karbantartás érdekében.

    1.  # Tervezés

        1.  # Szoftver architektúra

Az alkalmazás „Sketch”-e Processing 4 ben készült, melyet exportáltam
Java alkalmazássá, darabokra bontottam, és Java 19-es alkalmazásként
fejlesztettem tovább.

A Processing sketch önmagában a továbbiakban is elérhető.

2.  # Modell és nézet

    1.  # <img src="media/image13.png" style="width:8.24028in;height:5.90625in" />Osztály diagram

    2.  # Osztályok és főbb metódusaik

-   **Face:** → A kockán egy matricát reprezentál. Van egy színe, id-je,
    és egy irány amerre néz. Metódusai a „facek” forgatásáért és
    megjelenítéséért felelősek.

    -   **turnX():** → A kocka forgatásakor elforgatja a „face”-t a
        térben, az X tengely mentén, egy bizonyos fokkal.

    -   **turnY():** → A kocka forgatásakor elforgatja a „face”-t a
        térben, az Y tengely mentén, egy bizonyos fokkal.

    -   **turnZ():** → A kocka forgatásakor elforgatja a „face”-t a
        térben, a Z tengely mentén, egy bizonyos fokkal.

    -   **rotateFacingY():** → A „face” forgatását követően beállítja
        hogy a térben merre néz.

    -   **rotateFacingY():** → A „face” forgatását követően beállítja
        hogy a térben merre néz.

    -   **rotateFacingZ():** → A „face” forgatását követően beállítja
        hogy a térben merre néz.

-   **Cubie:** → A Rubik kocka egy kis elemét reprezentálja, ami lehet
    sarok, él, vagy akár a kocka magja. Metódusai a kocka térben
    forgatásáért, illetve a megjelenítéséért felelősek. Egy kockán 27
    található belőle.

    -   **turnFacesX():** → A kocka forgatásakor elforgatja a „cubie”-t
        a térben, az X tengely mentén, egy bizonyos irányba.

    -   **turnFacesY():** → A kocka forgatásakor elforgatja a „cubie”-t
        a térben, az Y tengely mentén, egy bizonyos irányba.

    -   **turnFacesZ():** → A kocka forgatásakor elforgatja a „cubie”-t
        a térben, az Z tengely mentén, egy bizonyos irányba.

    -   **update():** → A „cubie” saját pozícióját frissíti az aktuális
        forgatás után egy 3 dimenziós mátrixban.

    -   **show():** → A „cubie” és ezeken levő matricák megjelenítéséért
        felelős.

-   **Cube:** → Egy Rubik kockát reprezentál. A metódusai a kocka
    forgatásáért, megjelenítéséért, és a kirakott státusz
    megállapításáért felelnek.

    -   **checkIfAllEdgesAreSolved():** → Le ellenőrzi, minden él
        kirakottnak tekinthető-e a kockán.

    -   **checkIfAllCornersAreSolved():** → Le ellenőrzi, minden sarok
        kirakottnak tekinthető-e a kockán.

    -   **turnX():** → Elforgatja a kockát a választott tengelyen,
        irányba és oldalon.

    -   **turnY():** → Elforgatja a kockát a választott tengelyen,
        irányba és oldalon.

    -   **turnZ():** → Elforgatja a kockát a választott tengelyen,
        irányba és oldalon.

    -   **drawCube():** → A Rubik kocka megjelenítéséért felelős.

-   **Move:** → Az osztály egyetlen forgatást reprezentál egy megadott
    Rubik kockán. Tartalmazza a szükséges információkat a kocka
    adatainak frissítésére, és a lépés végrehajtására.

    -   **start():** → Elindítja a mozgatási animációt.

    -   **update():** → Frissíti, milyen szögben áll most a forgatás és
        annak animációja.

-   **RubiksCubeLogic:** A Rubik-kocka megoldásának alapvető logikáját
    tartalmazza az Old Pochmann módszer használatával. Felelős a kocka
    állapotáért, mozgatásáért, keveréséért és megoldási
    algoritmusokáért. Kezeli továbbá az él és a sarokpuffer logikát,
    szükség esetén megszakítja a „dugulást”.

    -   **shuffleCube():** → A kocka összekeveréséért feleős.

    -   **reverseAlgorithm()** → Visszafordít egy adott algoritmust.

    -   **solve():** → A kocka autómatikus megoldásáért felelős
        algoritmus.

    -   **breakPlugging():** → A kocka ezen megoldása esetén időnként
        előforduló úgynevetett „dugulás” elhárítására szolgáló metódus.

    -   **SolveOnePiece():** → Az éppen aktuális buffer elem
        megoldásáért felelős.

    -   **moveToTarget():** → Az éppen aktuális buffer elem helyét a
        csere pozícióba juttatja.

    -   **lookForEdgeBuffer():** → Megkeresi az él bufferben
        elhelyezkedő elemet.

    -   **lookForCornerBuffer():** → Megkeresi az sarok bufferben
        elhelyezkedő elemet.

    -   **logMove():** → A lépést egy a felhasználó által értelmezhető
        formátumban kiírja.

-   **AlgorithmCollection:** → Az adatbázisként használt JSON file-ból
    összegyűjti az OldPocham metódushoz szükséges algoritmusokat,
    továbbá a felhasználó által eltárolt algoritmusokat is. A fájl
    felépítése az [Adatbázis](#adatbázis) pontban tekinthető meg.

    -   **add():** → Egy algoritmust ad az adatbázishoz.

    -   **delete():** → Egy algoritmust eltávolít az adatbázisból.

-   **OldPochmanCollection():** → Az adatbázisként használt JSON
    file-ból összegyűjti az OldPocham metódushoz szükséges setupokat és
    a hozzájuk tartozó csere algoritmust. A felhasználó nem tud elemet
    adni az adatbázishoz. A fájl felépítése az [Adatbázis](#adatbázis)
    pontban tekinthető meg.

    -   **isItInAlgorithms():** → Megmondja, hogy a keresett lépés része
        e az adatbázisnak.

-   **AlgorithmCube:** → Ez az osztály felelős azért, hogy a felhasználó
    egy adott algorutmust gyakorolhasson a kockán. Az osztály figyeli a
    forgatásokat, visszajelez a felhasználónak, továbbá felelős a
    megjelenítésért is. Az egyik fő oka a modell nézet
    [ütközésének](#esetleges-eltérések-a-tervtől).

-   **GergosCube:** → Ennek az osztálynak a felelőssége a szabad játék,
    és a kocka önkirakása. Az animáció, a logika kezelése, és a
    Processing ablak megjelenítése a feladata. Az egyik fő oka a modell
    nézet [ütközésének](#esetleges-eltérések-a-tervtől).

    1.  # Adatbázis

Az alkalmazás adatbázisainak tekintet JSON fájlok az input almappában
helyezkednek el. Ezeknek a felépítése egyenként eltér egymástól, viszont
a kezelésük megegyezik.

# algorithms.JSON

Ez a fájl egy String kulcs, és String tömb érték párokat tartalmaz.
Ebben tároljuk a felhasználó által hozzáadott egyedi algoritmusokat, és
a program számára szükséges lépéssorozatokat is.

<img src="media/image14.png" style="width:6.10502in;height:1.63565in" alt="A képen szöveg, elektronika, billentyűzet látható Automatikusan generált leírás" />

# pochmanTargetPositions.JSON

Ez a fájl String kulcs és String tömb érték. Az értékként megadott tömb
első eleme egy string tömb, mely a megoldáshoz nélkülözhetetlen setup
algoritmusokat tartalmazzak, a második eleme pedig egy String, amely a
folytatáshoz szükséges csere algoritmus nevét tartalmazza. Ezt a
felhasználó nem tudja szerkeszteni. A setupok Szántai Szabolcs azaz a
[Kockások](#Kockasok) YouTube-csatorna készítőjétől származnak.

<img src="media/image15.png" style="width:5.25069in;height:2.53125in" />

# settings.JSON

<img src="media/image16.png" style="width:4.37778in;height:8.45278in" />Ez
a fájl a felhasználó saját beállításának tárolásáért felelős. A kulcs
mindig egy String, az érték változik a beállítástól függően.

# Speciális elemek

> • The Coding Train: Youtube csatorna, melyről az egész projekt
> gondolata származik. Itt láttam elöször az ötletet, mint a Rubik-Kocka
> Processingben való megvalósítása.
>
> • Kockások: Youtube csatorna, melyről a kocka vakon kirakásának
> tudását sajátítottam el, Szántai Szabolcs él és sarok setupjait
> haszálja a program.

1.  # Megvalósítás

    1.  # Felhasznált technológiák

<!-- -->

1.  **Processing könyvtár:**

> A könnyü és precíz kamerakezelés érdekében a Processing, egy PeasyCam
> könyvtárát használtam, melyet Jonathan Feinberg készített.
>
> A készítőt idézve: „A mouse driven camera-control library for 3D
> sketches.”

2.  **Java könyvtár:**

> A program megvalósításához használt további könyvtár a Gson. A leírást
> idézve:
>
> „Gson is a Java library that can be used to convert Java Objects into
> their JSON representation. It can also be used to convert a JSON
> string to an equivalent Java object.”
>
> Fordítása:
>
> „A Gson egy Java könyvtár, amelyet Java objektumok JSON
> reprezentációjává alakítására lehet használni. Ezenkívül használható
> JSON karakterlánc egyenértékű Java objektummá alakítására is.”

# Esetleges eltérések a tervtől

A Processing programnyelv használata az alkalmazás fejlesztésében
bizonyos kihívásokkal járt, amelyek megnehezítették munkát. Íme néhány
példa a felmerült problémákra:

-   **A nézet (view) és a modell (model) elkülönítése:** A Processing
    alapvetően a grafikus megjelenítésre és a vizuális művészetekre
    összpontosított, és nem biztosított különálló architektúrát a nézet
    és a modell elkülönítésére. Ez megnehezítette a MVC
    (Model-View-Controller) vagy hasonló tervezési minták alkalmazását.

-   **Tesztelhetőség:** A Processing alkalmazások tesztelése nehéz volt,
    mivel a programnyelv nem támogatta közvetlenül az automatizált
    tesztelési keretrendszereket.

-   **Alkalmazás bezárása:** A Processing alkalmazásokban a grafikus
    ablak bezárásakor az egész alkalmazás automatikusan leállt. Ez
    problémát okozott, ha csak egy adott részt akartam bezárni, vagy ha
    a bezárás után még folyamatban lévő műveleteket szerettem volna
    végrehajtani.

-   **Korlátozott könyvtárak és eszközök:** A Processing programnyelv
    néhány szakterületre szűkített könyvtára és eszköze lehet, hogy nem
    elegendő a fejlesztők számára, amikor bonyolultabb alkalmazásokat
    kell létrehozniuk.

-   **Teljesítmény problémák:** A Processing programnyelv, mivel magas
    szintű és könnyen használható, néha nem biztosít optimális
    teljesítményt.

-   **Kompatibilitás:** A Processing alapvetően Java alapú, de nem
    minden Java könyvtár vagy eszköz használható közvetlenül a
    Processingben.

-   **Grafikus felhasználói felület (GUI) tervezés:** A Processing nem
    rendelkezik beépített eszközökkel vagy könyvtárakkal a bonyolult
    grafikus felhasználói felületek létrehozásához.

Összességében a Processing programnyelv jelentős előnyökkel jár a
grafikus megjelenítés során, de számos kihívás is felmerülhet a
fejlesztők számára.

1.  # Tesztelés

    1.  # Egység tesztek

Ebben tesztelésre kerülnek az alábbi függvények:

-   AlgorithmCollection:

    -   add()

    -   delete()

-   OldPochmanCollection:

    -   isItInAlgorithms()

-   Cube:

    -   checkIfEdgesAreSolved()

    -   checkIfCornersAreSolved()

    -   turnX()

    -   turnY()

    -   turnZ()

-   Cubie

    -   turnFacesX()

    -   turnFacesY()

    -   turnFacesZ()

-   Face

    -   turnX()

    -   turnY()

    -   turnZ()

    -   rotateFacingX()

    -   rotateFacingY()

    -   rotateFacingZ()

        1.  # Végfelhasználói tesztesetek

# **Továbbfejlesztési lehetőségek**

-   Processing lecserélése, mint grafikus megoldás.

-   További metódusok implementálása, többféle kirakási módszer
    megtanítására.

-   Több méretű kocka implementálása.

-   Különböző formájú Rubik játékok implementálása, mint például a
    piraminx vagy megaminx.

-   Útkereső algoritmus implementálása, a legrövidebb setupok
    megkeresésére.

-   Mesterséges intelligencia beépítése, az önkirakás folyamatába.

-   A weboldal továbbfejlesztése, hogy még több információt adjon át a
    kirakással kapcsolatban

-   A kirakásban előre és hátra tekerés hozzáadása.

-   A kocka forgatási sebességének állítására lehetőség a
    beállításokban.

-   

# **Összefoglalás**

A szakdolgozatom célja egy olyan Rubik-kocka megalkotása volt, amely
képes segíteni a felhasználóknak megtanulni, hogyan lehet vakon kirakni
ezt. A motiváció a projekt mögött az volt, hogy hobbiként egy olyan
eszközt alkossak, amely összekapcsolja a szórakozást és a tanulást,
egyúttal fejleszti a logikai gondolkodást és a problémamegoldó
készségeket.

A fejlesztés alatt rengeteg alapvető tudást szereztem a Rubik-kocka
mögött rejlő algoritmusokról és megoldási módszerekről. A projekt egyik
fő célja a saját képességeim és ismereteim bővítése volt, miközben egy
olyan eszközt alkottam, amely azoknak segíthet, akik szeretnének
megismerkedni a Rubik-kocka világával.

Összességében ez a projekt egy személyes fejlődési utazás volt, amely
megtanított a kitartásra, a kreativitásra és arra, hogy a tanulás és a
szórakozás kéz a kézben járhat. Az alkalmazás létrehozása nemcsak az én
tudásom és képességeim fejlődését tükrözi, hanem mások számára is
inspirációt nyújt, akik hobbiként szeretnének új dolgokat megtanulni és
alkotni.

# **Irodalomjegyzék**

1.  Processing dokumentáció (online, linkelve: 2023.04.20.)  
    https://processing.org/reference/

2.  Java 19 dokumentáció (online, linkelve: 2023.04.20.)  
    https://docs.oracle.com/en/java/javase/19/

3.  GSON dokumentáció (online, linkelve: 2023.04.20.)  
    https://github.com/google/gson

4.  PeasyCam dokumentáció (online, linkelve: 2023.04.20.)  
    https://mrfeinberg.com/peasycam/

5.  <span id="Kockasok" class="anchor"></span>Kockások YouTube-csatorna
    (online, linkelve: 2023.04.20.)  
    https://www.youtube.com/@kockasok1903

6.  The Coding Train YouTube-csatorna (online, linkelve: 2023.04.20.)  
    https://www.youtube.com/@TheCodingTrain
