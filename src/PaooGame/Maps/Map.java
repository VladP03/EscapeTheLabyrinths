package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;               /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;                      /*!< Latimea hartii in numar de dale.*/
    private int height;                     /*!< Inaltimea hartii in numar de dale.*/

    private static boolean flagSpawnedMap1 = false;     /*!< Flag pentru generarea portalului din prima mapa.*/
    private static boolean flagSpawnedMap2 = false;     /*!< Flag pentru generarea portalului din cea de a 2-a mapa.*/
    private static boolean flagSpawnedMap3 = false;     /*!< Flag pentru generarea portalului din cea de a 3-a mapa.*/

    // 20 linii
    // 30 de coloane
    private int[][] map1 = new int[][] {                /*!< Tabloul bidimensional pentru prima mapa.*/
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, //0
            {5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, //1
            {5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //2
            {5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, //3
            {5, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, //4
            {5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 1, 0, 7, 2, 0, 5}, //5
            {5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 0, 5, 0, 5}, //6
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 0, 5}, //7
            {5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5}, //8
            {5, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 0, 0, 0, 5, 5, 0, 5, 0, 5}, //9
            {5, 5, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //10
            {5, 5, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //11
            {5, 5, 0, 5, 0, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //12
            {5, 5, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 1, 0, 5, 0, 5, 5, 0, 5, 0, 5}, //13
            {5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //14
            {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5}, //15
            {5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 0, 5}, //16
            {5, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 5, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 5}, //17
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5}, //18
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}  //19

    };

    // 30 linii
    // 41 de coloane
    private int[][] map2 = new int[][] {                /*!< Tabloul bidimensional pentru cea de a 2-a mapa.*/
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 0
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 1
            {5, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 2
            {5, 5, 0, 5, 0, 5, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5}, // 3
            {5, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 4
            {5, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 5
            {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 6
            {5, 0, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 7
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}, // 8
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 9
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5}, // 10
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 0, 5, 5, 5, 5, 5, 5}, // 11
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5}, // 12
            {5, 1, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 13
            {5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 14
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5, 5}, // 15
            {5, 0, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 16
            {5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 0, 5, 5}, // 17
            {5, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 1, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5}, // 18
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 0, 0, 0, 5}, // 19
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 20
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5}, // 21
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 22
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 23
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5}, // 24
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5}, // 25
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 0, 0, 5}, // 26
            {5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 27
            {5, 0, 0, 0, 0, 0, 1, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 28
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 29
    };

    // 30 linii
    // 41 de coloane
    private int[][] map3 = new int[][] {                    /*!< Tabloul bidimensional pentru cea de a 3-a mapa.*/
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 0
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 1
            {5, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 2
            {5, 5, 0, 5, 0, 5, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 3
            {5, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 4
            {5, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 5
            {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 6
            {5, 0, 0, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 7
            {5, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}, // 8
            {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 9
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5}, // 10
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 0, 5}, // 11
            {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5}, // 12
            {5, 1, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 13
            {5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 14
            {5, 0, 0, 0, 0, 0, 0, 0, 7, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5, 5}, // 15
            {5, 0, 5, 5, 5, 5, 5, 5, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 16
            {5, 0, 5, 5, 5, 5, 5, 5, 7, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 0, 5, 5}, // 17
            {5, 0, 5, 5, 5, 0, 0, 0, 7, 0, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 1, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5}, // 18
            {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 0, 0, 0, 5}, // 19
            {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 20
            {5, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5}, // 21
            {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 22
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 23
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5}, // 24
            {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5}, // 25
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 0, 0, 5}, // 26
            {5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 27
            {5, 0, 0, 0, 0, 0, 1, 5, 0, 5, 5, 1, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 28
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 29
    };

    private static int level = 1;           /*!< Referinta generarea a unui anumit nivel.*/


    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {
    }


    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        if (level == 1) {
            width = 30;
            height = 20;
            for (int y = 0; y < height; y++) { // refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT
                for (int x = 0; x < width; x++) { // refLink.GetGame().GetWidth() / Tile.TILE_WIDTH
                    GetTileForLoadingMap(x, y).Draw(g, (int) (x * Tile.TILE_WIDTH - refLink.GetGame().GetCameraGame().getxOffset()),
                            (int) (y * Tile.TILE_HEIGHT - refLink.GetGame().GetCameraGame().getyOffset()));
                }
            }

        } else if (level == 2) {
            width = 41;
            height = 30;
            for (int y = 0; y < height; y++) { // refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT
                for (int x = 0; x < width; x++) { // refLink.GetGame().GetWidth() / Tile.TILE_WIDTH
                    GetTileForLoadingMap(x, y).Draw(g, (int) (x * Tile.TILE_WIDTH - refLink.GetGame().GetCameraGame().getxOffset()),
                            (int) (y * Tile.TILE_HEIGHT - refLink.GetGame().GetCameraGame().getyOffset()));
                }
            }

        } else if (level == 3) {
            width = 41;
            height = 30;
            for (int y = 0; y < height; y++) { // refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT
                for (int x = 0; x < width; x++) { // refLink.GetGame().GetWidth() / Tile.TILE_WIDTH
                    GetTileForLoadingMap(x, y).Draw(g, (int) (x * Tile.TILE_WIDTH - refLink.GetGame().GetCameraGame().getxOffset()),
                            (int) (y * Tile.TILE_HEIGHT - refLink.GetGame().GetCameraGame().getyOffset()));
                }
            }
        }

    }


    /*! \fn public Tile GetTileForCollision(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTileForCollision(int x, int y) {

        if (level == 1) {
            if (Map1((int) (y + 35) / 50, (x + 23) / 50)  == 5) { // 33
                return Tile.wall;
            } else if (Map1((int) (y + 33) / 50, (x + 23) / 50) == 9) {
                return Tile.portal;
            } else if (Map1((int) (y + 33) / 50, (x + 23) / 50) == 7) {
                return Tile.lava;
            } else if (Map1((int) (y + 33) / 50, (x + 23) / 50) == 1) {
                return Tile.coin;
            } else if (Map1((int) (y + 33) / 50, (x + 23) / 50) == 2) {
                return Tile.speedPotion;
            }


        } else if (level == 2) {
            if (Map2((int) (y + 35) / 50, (x + 23) / 50)  == 5) { // 33
                return Tile.wall;
            } else if (Map2((int) (y + 33) / 50, (x + 23) / 50) == 9) {
                return Tile.portal;
            } else if (Map2((int) (y + 33) / 50, (x + 23) / 50) == 7) {
                return Tile.lava;
            } else if (Map2((int) (y + 33) / 50, (x + 23) / 50) == 1) {
                return Tile.coin;
            } else if (Map2((int) (y + 33) / 50, (x + 23) / 50) == 2) {
                return Tile.speedPotion;
            }

        } else if (level == 3) {
            if (Map3((int) (y + 35) / 50, (x + 23) / 50)  == 5) { // 33
                return Tile.wall;
            } else if (Map3((int) (y + 33) / 50, (x + 23) / 50) == 9) {
                return Tile.portal;
            } else if (Map3((int) (y + 33) / 50, (x + 23) / 50) == 7) {
                return Tile.lava;
            } else if (Map3((int) (y + 33) / 50, (x + 23) / 50) == 1) {
                return Tile.coin;
            } else if (Map3((int) (y + 33) / 50, (x + 23) / 50) == 2) {
                return Tile.speedPotion;
            }
        }

        return Tile.grass;
    }


    /*! \fn public Tile GetTileForLoadingMap(int x, int y)
    \brief Intoarce o referinta catre dala aferenta codului din matrice de dale pentru incarcarea hartii.
    */
    public Tile GetTileForLoadingMap(int x, int y) {

        if (level == 1) {

            if (Map1(y, x) == 5) {
                return Tile.wall;
            } else if (Map1(y, x) == 9) {
                return Tile.portal;
            } else if (Map1(y, x) == 0) {
                return Tile.grass;
            } else if (Map1(y, x) == 7) {
                return Tile.lava;
            } else if (Map1(y, x) == 1) {
                return Tile.coin;
            } else if (Map1(y, x) == 2) {
                return Tile.speedPotion;
            }

        } else if (level == 2) {
            if (Map2(y, x) == 5) {
                return Tile.wall;
            } else if (Map2(y, x) == 9) {
                return Tile.portal;
            } else if (Map2(y, x) == 0) {
                return Tile.grass;
            } else if (Map2(y, x) == 7) {
                return Tile.lava;
            } else if (Map2(y, x) == 1) {
                return Tile.coin;
            } else if (Map2(y, x) == 2) {
                return Tile.speedPotion;
            }

        } else if (level == 3) {
            if (Map3(y, x) == 5) {
                return Tile.wall;
            } else if (Map3(y, x) == 9) {
                return Tile.portal;
            } else if (Map3(y, x) == 0) {
                return Tile.grass;
            } else if (Map3(y, x) == 7) {
                return Tile.lava;
            } else if (Map3(y, x) == 1) {
                return Tile.coin;
            } else if (Map3(y, x) == 2) {
                return Tile.speedPotion;
            }
        }

        return Tile.grass;
    }


    /*! \fn public Tile gainCoinOrPotion(int x, int y)
    \brief Intoarce o referinta catre dala aferenta codului din matrice de dale pentru a verifica daca dala respectiva este de tip banut sau potiune de viteza.
    */
    public void gainCoinOrPotion(int x, int y) {
        if (level == 1) {
            map1[(int) (y + 33) / 50][(int)(x + 23) / 50 ] = 0;
        }

        if (level == 2) {
            map2[(int) (y + 33) / 50][(int)(x + 23) / 50 ] = 0;
        }

        if (level == 3) {
            map3[(int) (y + 33) / 50][(int)(x + 23) / 50 ] = 0;
        }

    }


    /*! \fn public void Reset()
    \brief Reseteaza mapele pentru un joc nou.
    */
    public void Reset() {
        map1 = new int[][]{
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, //0
                {5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, //1
                {5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //2
                {5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, //3
                {5, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, //4
                {5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 1, 0, 7, 2, 0, 5}, //5
                {5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 0, 5, 0, 5}, //6
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 0, 5}, //7
                {5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5}, //8
                {5, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 0, 0, 0, 5, 5, 0, 5, 0, 5}, //9
                {5, 5, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //10
                {5, 5, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //11
                {5, 5, 0, 5, 0, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //12
                {5, 5, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 1, 0, 5, 0, 5, 5, 0, 5, 0, 5}, //13
                {5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5}, //14
                {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5}, //15
                {5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 0, 5}, //16
                {5, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 5, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 5}, //17
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5}, //18
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}  //19

        };

        map2 = new int[][] {
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 0
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 1
                {5, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 2
                {5, 5, 0, 5, 0, 5, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5}, // 3
                {5, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 4
                {5, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 5
                {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 6
                {5, 0, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 7
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}, // 8
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 9
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5}, // 10
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 0, 5, 5, 5, 5, 5, 5}, // 11
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5}, // 12
                {5, 1, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 13
                {5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 14
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5, 5}, // 15
                {5, 0, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 16
                {5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 0, 5, 5}, // 17
                {5, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 1, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5}, // 18
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 0, 0, 0, 5}, // 19
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 20
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5}, // 21
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 22
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 23
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5}, // 24
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5}, // 25
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 0, 0, 5}, // 26
                {5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 27
                {5, 0, 0, 0, 0, 0, 1, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 28
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 29
        };

        map3 = new int[][] {
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 0
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 1
                {5, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 2
                {5, 5, 0, 5, 0, 5, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 0, 5, 5}, // 3
                {5, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 4
                {5, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 5
                {5, 0, 0, 5, 0, 0, 0, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 6
                {5, 0, 0, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 7
                {5, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}, // 8
                {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 9
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5}, // 10
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 0, 5}, // 11
                {5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5}, // 12
                {5, 1, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 13
                {5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5}, // 14
                {5, 0, 0, 0, 0, 0, 0, 0, 7, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5, 5}, // 15
                {5, 0, 5, 5, 5, 5, 5, 5, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 16
                {5, 0, 5, 5, 5, 5, 5, 5, 7, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 0, 5, 5}, // 17
                {5, 0, 5, 5, 5, 0, 0, 0, 7, 0, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 1, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5}, // 18
                {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 0, 0, 0, 5}, // 19
                {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 20
                {5, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 5}, // 21
                {5, 0, 5, 5, 5, 0, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5}, // 22
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 0, 0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5}, // 23
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5}, // 24
                {5, 0, 5, 5, 0, 0, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5}, // 25
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5, 5, 0, 5, 0, 0, 0, 5}, // 26
                {5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5}, // 27
                {5, 0, 0, 0, 0, 0, 1, 5, 0, 5, 5, 1, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 28
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // 29
        };

        setLevel(1);
        flagSpawnedMap1 = false;
        flagSpawnedMap2 = false;
        flagSpawnedMap3 = false;
    }


    /*! \fn public int Map1(int x, int y)
        \brief Returneaza id-ul dalii din prima mapa, din pozitia respectiva.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    public int Map1(int x, int y) {
        return map1[x][y];
    }


    /*! \fn public int Map2(int x, int y)
    \brief Returneaza id-ul dalii din a 2-a mapa, din pozitia respectiva.

    \param x linia pe care se afla codul dalei de interes.
    \param y coloana pe care se afla codul dalei de interes.
    */
    public int Map2(int x, int y) {
        return map2[x][y];
    }


    /*! \fn public int Map3(int x, int y)
    \brief Returneaza id-ul dalii din a 3-a mapa, din pozitia respectiva.

    \param x linia pe care se afla codul dalei de interes.
    \param y coloana pe care se afla codul dalei de interes.
    */
    public int Map3(int x, int y) {
        return map3[x][y];
    }


    /*! \fn public void SetMap(int x, int y, int val)
    \brief Seteaza mapa in cazul in care se face un load.

    \param x linia pe care se afla codul dalei de interes.
    \param y coloana pe care se afla codul dalei de interes.
    \param val id-ul dalii respective.
    */
    public void SetMap(int x, int y, int val) {
        if (level == 1) {
            map1[x][y] = val;
            if (val == 9) {
                flagSpawnedMap1 = true;
            }
        } else if (level == 2) {
            map2[x][y] = val;
            if (val == 9) {
                flagSpawnedMap2 = true;
            }
        } else if (level == 3) {
            map3[x][y] = val;
            if (val == 9) {
                flagSpawnedMap3 = true;
            }
        }
    }


    /*! \fn public void SetMap(int x, int y, int val)
        \brief Cand se colecteaza toti banii din mapa respectiva se va genera un numar random (0-9) si portal se poate genera in una din cele 11 pozitii.
    */
    public void SetPortal() {
        if (level == 1) {
            int randomValue = (int)(Math.random() * 9);
            if (flagSpawnedMap1 == false) {
                switch (randomValue) {
                    case 0:
                        map1[17][29] = 9;
                        break;
                    case 1:
                        map1[19][26] = 9;
                        break;
                    case 2:
                        map1[8][29] = 9;
                        break;
                    case 3:
                        map1[0][19] = 9;
                        break;
                    case 4:
                        map1[7][0] = 9;
                        break;
                    case 5:
                        map1[19][15] = 9;
                        break;
                    case 6:
                        map1[3][0] = 9;
                        break;
                    case 7:
                        map1[5][0] = 9;
                        break;
                    case 8:
                        map1[17][0] = 9;
                        break;
                    default:
                        map1[2][29] = 9;
                        break;
                }
                flagSpawnedMap1 = true;
            }

        } else if (level == 2) {
            int randomValue = (int)(Math.random() * 9);

            if (flagSpawnedMap2 == false) {
                switch (randomValue) {
                    case 0:
                        map2[5][0] = 9;
                        break;
                    case 1:
                        map2[1][40] = 9;
                        break;
                    case 2:
                        map2[6][40] = 9;
                        break;
                    case 3:
                        map2[10][40] = 9;
                        break;
                    case 4:
                        map2[26][40] = 9;
                        break;
                    case 5:
                        map2[28][0] = 9;
                        break;
                    case 6:
                        map2[24][0] = 9;
                        break;
                    case 7:
                        map2[0][30] = 9;
                        break;
                    case 8:
                        map2[1][0] = 9;
                        break;
                    default:
                        map2[29][15] = 9;
                        break;
                }
                flagSpawnedMap2 = true;
            }

        } else if (level == 3) {
            int randomValue = (int) (Math.random() * 9);

            if (flagSpawnedMap3 == false) {
                switch (randomValue) {
                    case 0:
                        map3[19][0] = 9;
                        break;
                    case 1:
                        map3[29][8] = 9;
                        break;
                    case 2:
                        map3[29][15] = 9;
                        break;
                    case 3:
                        map3[29][1] = 9;
                        break;
                    case 4:
                        map3[1][0] = 9;
                        break;
                    case 5:
                        map3[1][40] = 9;
                        break;
                    case 6:
                        map3[6][40] = 9;
                        break;
                    case 7:
                        map3[26][40] = 9;
                        break;
                    case 8:
                        map3[0][30] = 9;
                        break;
                    default:
                        map3[4][40] = 9;
                        break;
                }
                flagSpawnedMap3 = true;
            }
        }
    }


    /*! \fn public int getWidth()
    \brief Returneaza latimea(numarul de coloane) mapei respective.
    */
    public int getWidth() {
        return width;
    }


    /*! \fn public int getHeight()
    \brief Returneaza inaltimea(numarul de linii) mapei respective.
    */
    public int getHeight() {
        return height;
    }


    /*! \fn public static int getLevel()
    \brief Returneaza nivelul curent.
    */
    public static int getLevel() {
        return level;
    }


    /*! \fn public static void setLevel(int level)
        \brief Seteaza nivelul.

        \param level este nivelul dorit.
    */
    public static void setLevel(int level) {
        Map.level = level;
    }


    /*! \fn public void setWidth(int width)
    \brief Seteaza latimea unei mape.

    \param width este latimea dorita.
    */
    public void setWidth(int width) {
        this.width = width;
    }


    /*! \fn public void setHeight(int height)
        \brief Seteaza inaltimea unei mape.

        \param height este latimea dorita.
    */
    public void setHeight(int height) {
        this.height = height;
    }


    /*! \fn public static void setFlagSpawnedMap1(boolean flagSpawnedMap1)
    \brief Seteaza flag-ul de generare a portalului in prima mapa.

    \param flagSpawnedMap1 este valoarea flag-ului.
    */
    public static void setFlagSpawnedMap1(boolean flagSpawnedMap1) {
        Map.flagSpawnedMap1 = flagSpawnedMap1;
    }


    /*! \fn public static void setFlagSpawnedMap2(boolean flagSpawnedMap2)
        \brief Seteaza flag-ul de generare a portalului in a 2-a mapa.

        \param setFlagSpawnedMap2 este valoarea flag-ului.
    */
    public static void setFlagSpawnedMap2(boolean flagSpawnedMap2) {
        Map.flagSpawnedMap2 = flagSpawnedMap2;
    }


    /*! \fn public static void setFlagSpawnedMap2(boolean flagSpawnedMap3)
    \brief Seteaza flag-ul de generare a portalului in a 3-a mapa.

    \param setFlagSpawnedMap3 este valoarea flag-ului.
    */
    public static void setFlagSpawnedMap3(boolean flagSpawnedMap3) {
        Map.flagSpawnedMap3 = flagSpawnedMap3;
    }
}
