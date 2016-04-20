/*
 * Copyright © 2009-2016 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

// This file was generated automatically by the Snowball to Java compiler

package org.apromore.similaritysearch.common.stemmer.ext;

import org.apromore.similaritysearch.common.stemmer.Among;

/**
 * This class was automatically generated by a Snowball to Java compiler
 * It implements the stemming algorithm defined by a snowball script.
 */

public class portugueseStemmer extends org.apromore.similaritysearch.common.stemmer.SnowballStemmer {

    private static final long serialVersionUID = 1L;

    private final static portugueseStemmer methodObject = new portugueseStemmer();

    private final static Among a_0[] = {
            new Among("", -1, 3, "", methodObject),
            new Among("\u00E3", 0, 1, "", methodObject),
            new Among("\u00F5", 0, 2, "", methodObject)
    };

    private final static Among a_1[] = {
            new Among("", -1, 3, "", methodObject),
            new Among("a~", 0, 1, "", methodObject),
            new Among("o~", 0, 2, "", methodObject)
    };

    private final static Among a_2[] = {
            new Among("ic", -1, -1, "", methodObject),
            new Among("ad", -1, -1, "", methodObject),
            new Among("os", -1, -1, "", methodObject),
            new Among("iv", -1, 1, "", methodObject)
    };

    private final static Among a_3[] = {
            new Among("ante", -1, 1, "", methodObject),
            new Among("avel", -1, 1, "", methodObject),
            new Among("\u00EDvel", -1, 1, "", methodObject)
    };

    private final static Among a_4[] = {
            new Among("ic", -1, 1, "", methodObject),
            new Among("abil", -1, 1, "", methodObject),
            new Among("iv", -1, 1, "", methodObject)
    };

    private final static Among a_5[] = {
            new Among("ica", -1, 1, "", methodObject),
            new Among("\u00E2ncia", -1, 1, "", methodObject),
            new Among("\u00EAncia", -1, 4, "", methodObject),
            new Among("ira", -1, 9, "", methodObject),
            new Among("adora", -1, 1, "", methodObject),
            new Among("osa", -1, 1, "", methodObject),
            new Among("ista", -1, 1, "", methodObject),
            new Among("iva", -1, 8, "", methodObject),
            new Among("eza", -1, 1, "", methodObject),
            new Among("log\u00EDa", -1, 2, "", methodObject),
            new Among("idade", -1, 7, "", methodObject),
            new Among("ante", -1, 1, "", methodObject),
            new Among("mente", -1, 6, "", methodObject),
            new Among("amente", 12, 5, "", methodObject),
            new Among("\u00E1vel", -1, 1, "", methodObject),
            new Among("\u00EDvel", -1, 1, "", methodObject),
            new Among("uci\u00F3n", -1, 3, "", methodObject),
            new Among("ico", -1, 1, "", methodObject),
            new Among("ismo", -1, 1, "", methodObject),
            new Among("oso", -1, 1, "", methodObject),
            new Among("amento", -1, 1, "", methodObject),
            new Among("imento", -1, 1, "", methodObject),
            new Among("ivo", -1, 8, "", methodObject),
            new Among("a\u00E7a~o", -1, 1, "", methodObject),
            new Among("ador", -1, 1, "", methodObject),
            new Among("icas", -1, 1, "", methodObject),
            new Among("\u00EAncias", -1, 4, "", methodObject),
            new Among("iras", -1, 9, "", methodObject),
            new Among("adoras", -1, 1, "", methodObject),
            new Among("osas", -1, 1, "", methodObject),
            new Among("istas", -1, 1, "", methodObject),
            new Among("ivas", -1, 8, "", methodObject),
            new Among("ezas", -1, 1, "", methodObject),
            new Among("log\u00EDas", -1, 2, "", methodObject),
            new Among("idades", -1, 7, "", methodObject),
            new Among("uciones", -1, 3, "", methodObject),
            new Among("adores", -1, 1, "", methodObject),
            new Among("antes", -1, 1, "", methodObject),
            new Among("a\u00E7o~es", -1, 1, "", methodObject),
            new Among("icos", -1, 1, "", methodObject),
            new Among("ismos", -1, 1, "", methodObject),
            new Among("osos", -1, 1, "", methodObject),
            new Among("amentos", -1, 1, "", methodObject),
            new Among("imentos", -1, 1, "", methodObject),
            new Among("ivos", -1, 8, "", methodObject)
    };

    private final static Among a_6[] = {
            new Among("ada", -1, 1, "", methodObject),
            new Among("ida", -1, 1, "", methodObject),
            new Among("ia", -1, 1, "", methodObject),
            new Among("aria", 2, 1, "", methodObject),
            new Among("eria", 2, 1, "", methodObject),
            new Among("iria", 2, 1, "", methodObject),
            new Among("ara", -1, 1, "", methodObject),
            new Among("era", -1, 1, "", methodObject),
            new Among("ira", -1, 1, "", methodObject),
            new Among("ava", -1, 1, "", methodObject),
            new Among("asse", -1, 1, "", methodObject),
            new Among("esse", -1, 1, "", methodObject),
            new Among("isse", -1, 1, "", methodObject),
            new Among("aste", -1, 1, "", methodObject),
            new Among("este", -1, 1, "", methodObject),
            new Among("iste", -1, 1, "", methodObject),
            new Among("ei", -1, 1, "", methodObject),
            new Among("arei", 16, 1, "", methodObject),
            new Among("erei", 16, 1, "", methodObject),
            new Among("irei", 16, 1, "", methodObject),
            new Among("am", -1, 1, "", methodObject),
            new Among("iam", 20, 1, "", methodObject),
            new Among("ariam", 21, 1, "", methodObject),
            new Among("eriam", 21, 1, "", methodObject),
            new Among("iriam", 21, 1, "", methodObject),
            new Among("aram", 20, 1, "", methodObject),
            new Among("eram", 20, 1, "", methodObject),
            new Among("iram", 20, 1, "", methodObject),
            new Among("avam", 20, 1, "", methodObject),
            new Among("em", -1, 1, "", methodObject),
            new Among("arem", 29, 1, "", methodObject),
            new Among("erem", 29, 1, "", methodObject),
            new Among("irem", 29, 1, "", methodObject),
            new Among("assem", 29, 1, "", methodObject),
            new Among("essem", 29, 1, "", methodObject),
            new Among("issem", 29, 1, "", methodObject),
            new Among("ado", -1, 1, "", methodObject),
            new Among("ido", -1, 1, "", methodObject),
            new Among("ando", -1, 1, "", methodObject),
            new Among("endo", -1, 1, "", methodObject),
            new Among("indo", -1, 1, "", methodObject),
            new Among("ara~o", -1, 1, "", methodObject),
            new Among("era~o", -1, 1, "", methodObject),
            new Among("ira~o", -1, 1, "", methodObject),
            new Among("ar", -1, 1, "", methodObject),
            new Among("er", -1, 1, "", methodObject),
            new Among("ir", -1, 1, "", methodObject),
            new Among("as", -1, 1, "", methodObject),
            new Among("adas", 47, 1, "", methodObject),
            new Among("idas", 47, 1, "", methodObject),
            new Among("ias", 47, 1, "", methodObject),
            new Among("arias", 50, 1, "", methodObject),
            new Among("erias", 50, 1, "", methodObject),
            new Among("irias", 50, 1, "", methodObject),
            new Among("aras", 47, 1, "", methodObject),
            new Among("eras", 47, 1, "", methodObject),
            new Among("iras", 47, 1, "", methodObject),
            new Among("avas", 47, 1, "", methodObject),
            new Among("es", -1, 1, "", methodObject),
            new Among("ardes", 58, 1, "", methodObject),
            new Among("erdes", 58, 1, "", methodObject),
            new Among("irdes", 58, 1, "", methodObject),
            new Among("ares", 58, 1, "", methodObject),
            new Among("eres", 58, 1, "", methodObject),
            new Among("ires", 58, 1, "", methodObject),
            new Among("asses", 58, 1, "", methodObject),
            new Among("esses", 58, 1, "", methodObject),
            new Among("isses", 58, 1, "", methodObject),
            new Among("astes", 58, 1, "", methodObject),
            new Among("estes", 58, 1, "", methodObject),
            new Among("istes", 58, 1, "", methodObject),
            new Among("is", -1, 1, "", methodObject),
            new Among("ais", 71, 1, "", methodObject),
            new Among("eis", 71, 1, "", methodObject),
            new Among("areis", 73, 1, "", methodObject),
            new Among("ereis", 73, 1, "", methodObject),
            new Among("ireis", 73, 1, "", methodObject),
            new Among("\u00E1reis", 73, 1, "", methodObject),
            new Among("\u00E9reis", 73, 1, "", methodObject),
            new Among("\u00EDreis", 73, 1, "", methodObject),
            new Among("\u00E1sseis", 73, 1, "", methodObject),
            new Among("\u00E9sseis", 73, 1, "", methodObject),
            new Among("\u00EDsseis", 73, 1, "", methodObject),
            new Among("\u00E1veis", 73, 1, "", methodObject),
            new Among("\u00EDeis", 73, 1, "", methodObject),
            new Among("ar\u00EDeis", 84, 1, "", methodObject),
            new Among("er\u00EDeis", 84, 1, "", methodObject),
            new Among("ir\u00EDeis", 84, 1, "", methodObject),
            new Among("ados", -1, 1, "", methodObject),
            new Among("idos", -1, 1, "", methodObject),
            new Among("amos", -1, 1, "", methodObject),
            new Among("\u00E1ramos", 90, 1, "", methodObject),
            new Among("\u00E9ramos", 90, 1, "", methodObject),
            new Among("\u00EDramos", 90, 1, "", methodObject),
            new Among("\u00E1vamos", 90, 1, "", methodObject),
            new Among("\u00EDamos", 90, 1, "", methodObject),
            new Among("ar\u00EDamos", 95, 1, "", methodObject),
            new Among("er\u00EDamos", 95, 1, "", methodObject),
            new Among("ir\u00EDamos", 95, 1, "", methodObject),
            new Among("emos", -1, 1, "", methodObject),
            new Among("aremos", 99, 1, "", methodObject),
            new Among("eremos", 99, 1, "", methodObject),
            new Among("iremos", 99, 1, "", methodObject),
            new Among("\u00E1ssemos", 99, 1, "", methodObject),
            new Among("\u00EAssemos", 99, 1, "", methodObject),
            new Among("\u00EDssemos", 99, 1, "", methodObject),
            new Among("imos", -1, 1, "", methodObject),
            new Among("armos", -1, 1, "", methodObject),
            new Among("ermos", -1, 1, "", methodObject),
            new Among("irmos", -1, 1, "", methodObject),
            new Among("\u00E1mos", -1, 1, "", methodObject),
            new Among("ar\u00E1s", -1, 1, "", methodObject),
            new Among("er\u00E1s", -1, 1, "", methodObject),
            new Among("ir\u00E1s", -1, 1, "", methodObject),
            new Among("eu", -1, 1, "", methodObject),
            new Among("iu", -1, 1, "", methodObject),
            new Among("ou", -1, 1, "", methodObject),
            new Among("ar\u00E1", -1, 1, "", methodObject),
            new Among("er\u00E1", -1, 1, "", methodObject),
            new Among("ir\u00E1", -1, 1, "", methodObject)
    };

    private final static Among a_7[] = {
            new Among("a", -1, 1, "", methodObject),
            new Among("i", -1, 1, "", methodObject),
            new Among("o", -1, 1, "", methodObject),
            new Among("os", -1, 1, "", methodObject),
            new Among("\u00E1", -1, 1, "", methodObject),
            new Among("\u00ED", -1, 1, "", methodObject),
            new Among("\u00F3", -1, 1, "", methodObject)
    };

    private final static Among a_8[] = {
            new Among("e", -1, 1, "", methodObject),
            new Among("\u00E7", -1, 2, "", methodObject),
            new Among("\u00E9", -1, 1, "", methodObject),
            new Among("\u00EA", -1, 1, "", methodObject)
    };

    private static final char g_v[] = {17, 65, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 19, 12, 2};

    private int I_p2;
    private int I_p1;
    private int I_pV;

    private void copy_from(portugueseStemmer other) {
        I_p2 = other.I_p2;
        I_p1 = other.I_p1;
        I_pV = other.I_pV;
        super.copy_from(other);
    }

    private boolean r_prelude() {
        int among_var;
        int v_1;
        // repeat, line 36
        replab0:
        while (true) {
            v_1 = cursor;
            lab1:
            do {
                // (, line 36
                // [, line 37
                bra = cursor;
                // substring, line 37
                among_var = find_among(a_0, 3);
                if (among_var == 0) {
                    break lab1;
                }
                // ], line 37
                ket = cursor;
                switch (among_var) {
                    case 0:
                        break lab1;
                    case 1:
                        // (, line 38
                        // <-, line 38
                        slice_from("a~");
                        break;
                    case 2:
                        // (, line 39
                        // <-, line 39
                        slice_from("o~");
                        break;
                    case 3:
                        // (, line 40
                        // next, line 40
                        if (cursor >= limit) {
                            break lab1;
                        }
                        cursor++;
                        break;
                }
                continue replab0;
            } while (false);
            cursor = v_1;
            break replab0;
        }
        return true;
    }

    private boolean r_mark_regions() {
        int v_1;
        int v_2;
        int v_3;
        int v_6;
        int v_8;
        // (, line 44
        I_pV = limit;
        I_p1 = limit;
        I_p2 = limit;
        // do, line 50
        v_1 = cursor;
        lab0:
        do {
            // (, line 50
            // or, line 52
            lab1:
            do {
                v_2 = cursor;
                lab2:
                do {
                    // (, line 51
                    if (!(in_grouping(g_v, 97, 250))) {
                        break lab2;
                    }
                    // or, line 51
                    lab3:
                    do {
                        v_3 = cursor;
                        lab4:
                        do {
                            // (, line 51
                            if (!(out_grouping(g_v, 97, 250))) {
                                break lab4;
                            }
                            // gopast, line 51
                            golab5:
                            while (true) {
                                lab6:
                                do {
                                    if (!(in_grouping(g_v, 97, 250))) {
                                        break lab6;
                                    }
                                    break golab5;
                                } while (false);
                                if (cursor >= limit) {
                                    break lab4;
                                }
                                cursor++;
                            }
                            break lab3;
                        } while (false);
                        cursor = v_3;
                        // (, line 51
                        if (!(in_grouping(g_v, 97, 250))) {
                            break lab2;
                        }
                        // gopast, line 51
                        golab7:
                        while (true) {
                            lab8:
                            do {
                                if (!(out_grouping(g_v, 97, 250))) {
                                    break lab8;
                                }
                                break golab7;
                            } while (false);
                            if (cursor >= limit) {
                                break lab2;
                            }
                            cursor++;
                        }
                    } while (false);
                    break lab1;
                } while (false);
                cursor = v_2;
                // (, line 53
                if (!(out_grouping(g_v, 97, 250))) {
                    break lab0;
                }
                // or, line 53
                lab9:
                do {
                    v_6 = cursor;
                    lab10:
                    do {
                        // (, line 53
                        if (!(out_grouping(g_v, 97, 250))) {
                            break lab10;
                        }
                        // gopast, line 53
                        golab11:
                        while (true) {
                            lab12:
                            do {
                                if (!(in_grouping(g_v, 97, 250))) {
                                    break lab12;
                                }
                                break golab11;
                            } while (false);
                            if (cursor >= limit) {
                                break lab10;
                            }
                            cursor++;
                        }
                        break lab9;
                    } while (false);
                    cursor = v_6;
                    // (, line 53
                    if (!(in_grouping(g_v, 97, 250))) {
                        break lab0;
                    }
                    // next, line 53
                    if (cursor >= limit) {
                        break lab0;
                    }
                    cursor++;
                } while (false);
            } while (false);
            // setmark pV, line 54
            I_pV = cursor;
        } while (false);
        cursor = v_1;
        // do, line 56
        v_8 = cursor;
        lab13:
        do {
            // (, line 56
            // gopast, line 57
            golab14:
            while (true) {
                lab15:
                do {
                    if (!(in_grouping(g_v, 97, 250))) {
                        break lab15;
                    }
                    break golab14;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // gopast, line 57
            golab16:
            while (true) {
                lab17:
                do {
                    if (!(out_grouping(g_v, 97, 250))) {
                        break lab17;
                    }
                    break golab16;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // setmark p1, line 57
            I_p1 = cursor;
            // gopast, line 58
            golab18:
            while (true) {
                lab19:
                do {
                    if (!(in_grouping(g_v, 97, 250))) {
                        break lab19;
                    }
                    break golab18;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // gopast, line 58
            golab20:
            while (true) {
                lab21:
                do {
                    if (!(out_grouping(g_v, 97, 250))) {
                        break lab21;
                    }
                    break golab20;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // setmark p2, line 58
            I_p2 = cursor;
        } while (false);
        cursor = v_8;
        return true;
    }

    private boolean r_postlude() {
        int among_var;
        int v_1;
        // repeat, line 62
        replab0:
        while (true) {
            v_1 = cursor;
            lab1:
            do {
                // (, line 62
                // [, line 63
                bra = cursor;
                // substring, line 63
                among_var = find_among(a_1, 3);
                if (among_var == 0) {
                    break lab1;
                }
                // ], line 63
                ket = cursor;
                switch (among_var) {
                    case 0:
                        break lab1;
                    case 1:
                        // (, line 64
                        // <-, line 64
                        slice_from("\u00E3");
                        break;
                    case 2:
                        // (, line 65
                        // <-, line 65
                        slice_from("\u00F5");
                        break;
                    case 3:
                        // (, line 66
                        // next, line 66
                        if (cursor >= limit) {
                            break lab1;
                        }
                        cursor++;
                        break;
                }
                continue replab0;
            } while (false);
            cursor = v_1;
            break replab0;
        }
        return true;
    }

    private boolean r_RV() {
        if (!(I_pV <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_R1() {
        if (!(I_p1 <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_R2() {
        if (!(I_p2 <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_standard_suffix() {
        int among_var;
        int v_1;
        int v_2;
        int v_3;
        int v_4;
        // (, line 76
        // [, line 77
        ket = cursor;
        // substring, line 77
        among_var = find_among_b(a_5, 45);
        if (among_var == 0) {
            return false;
        }
        // ], line 77
        bra = cursor;
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 92
                // call R2, line 93
                if (!r_R2()) {
                    return false;
                }
                // delete, line 93
                slice_del();
                break;
            case 2:
                // (, line 97
                // call R2, line 98
                if (!r_R2()) {
                    return false;
                }
                // <-, line 98
                slice_from("log");
                break;
            case 3:
                // (, line 101
                // call R2, line 102
                if (!r_R2()) {
                    return false;
                }
                // <-, line 102
                slice_from("u");
                break;
            case 4:
                // (, line 105
                // call R2, line 106
                if (!r_R2()) {
                    return false;
                }
                // <-, line 106
                slice_from("ente");
                break;
            case 5:
                // (, line 109
                // call R1, line 110
                if (!r_R1()) {
                    return false;
                }
                // delete, line 110
                slice_del();
                // try, line 111
                v_1 = limit - cursor;
                lab0:
                do {
                    // (, line 111
                    // [, line 112
                    ket = cursor;
                    // substring, line 112
                    among_var = find_among_b(a_2, 4);
                    if (among_var == 0) {
                        cursor = limit - v_1;
                        break lab0;
                    }
                    // ], line 112
                    bra = cursor;
                    // call R2, line 112
                    if (!r_R2()) {
                        cursor = limit - v_1;
                        break lab0;
                    }
                    // delete, line 112
                    slice_del();
                    switch (among_var) {
                        case 0:
                            cursor = limit - v_1;
                            break lab0;
                        case 1:
                            // (, line 113
                            // [, line 113
                            ket = cursor;
                            // literal, line 113
                            if (!(eq_s_b(2, "at"))) {
                                cursor = limit - v_1;
                                break lab0;
                            }
                            // ], line 113
                            bra = cursor;
                            // call R2, line 113
                            if (!r_R2()) {
                                cursor = limit - v_1;
                                break lab0;
                            }
                            // delete, line 113
                            slice_del();
                            break;
                    }
                } while (false);
                break;
            case 6:
                // (, line 121
                // call R2, line 122
                if (!r_R2()) {
                    return false;
                }
                // delete, line 122
                slice_del();
                // try, line 123
                v_2 = limit - cursor;
                lab1:
                do {
                    // (, line 123
                    // [, line 124
                    ket = cursor;
                    // substring, line 124
                    among_var = find_among_b(a_3, 3);
                    if (among_var == 0) {
                        cursor = limit - v_2;
                        break lab1;
                    }
                    // ], line 124
                    bra = cursor;
                    switch (among_var) {
                        case 0:
                            cursor = limit - v_2;
                            break lab1;
                        case 1:
                            // (, line 127
                            // call R2, line 127
                            if (!r_R2()) {
                                cursor = limit - v_2;
                                break lab1;
                            }
                            // delete, line 127
                            slice_del();
                            break;
                    }
                } while (false);
                break;
            case 7:
                // (, line 133
                // call R2, line 134
                if (!r_R2()) {
                    return false;
                }
                // delete, line 134
                slice_del();
                // try, line 135
                v_3 = limit - cursor;
                lab2:
                do {
                    // (, line 135
                    // [, line 136
                    ket = cursor;
                    // substring, line 136
                    among_var = find_among_b(a_4, 3);
                    if (among_var == 0) {
                        cursor = limit - v_3;
                        break lab2;
                    }
                    // ], line 136
                    bra = cursor;
                    switch (among_var) {
                        case 0:
                            cursor = limit - v_3;
                            break lab2;
                        case 1:
                            // (, line 139
                            // call R2, line 139
                            if (!r_R2()) {
                                cursor = limit - v_3;
                                break lab2;
                            }
                            // delete, line 139
                            slice_del();
                            break;
                    }
                } while (false);
                break;
            case 8:
                // (, line 145
                // call R2, line 146
                if (!r_R2()) {
                    return false;
                }
                // delete, line 146
                slice_del();
                // try, line 147
                v_4 = limit - cursor;
                lab3:
                do {
                    // (, line 147
                    // [, line 148
                    ket = cursor;
                    // literal, line 148
                    if (!(eq_s_b(2, "at"))) {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    // ], line 148
                    bra = cursor;
                    // call R2, line 148
                    if (!r_R2()) {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    // delete, line 148
                    slice_del();
                } while (false);
                break;
            case 9:
                // (, line 152
                // call RV, line 153
                if (!r_RV()) {
                    return false;
                }
                // literal, line 153
                if (!(eq_s_b(1, "e"))) {
                    return false;
                }
                // <-, line 154
                slice_from("ir");
                break;
        }
        return true;
    }

    private boolean r_verb_suffix() {
        int among_var;
        int v_1;
        int v_2;
        // setlimit, line 159
        v_1 = limit - cursor;
        // tomark, line 159
        if (cursor < I_pV) {
            return false;
        }
        cursor = I_pV;
        v_2 = limit_backward;
        limit_backward = cursor;
        cursor = limit - v_1;
        // (, line 159
        // [, line 160
        ket = cursor;
        // substring, line 160
        among_var = find_among_b(a_6, 120);
        if (among_var == 0) {
            limit_backward = v_2;
            return false;
        }
        // ], line 160
        bra = cursor;
        switch (among_var) {
            case 0:
                limit_backward = v_2;
                return false;
            case 1:
                // (, line 179
                // delete, line 179
                slice_del();
                break;
        }
        limit_backward = v_2;
        return true;
    }

    private boolean r_residual_suffix() {
        int among_var;
        // (, line 183
        // [, line 184
        ket = cursor;
        // substring, line 184
        among_var = find_among_b(a_7, 7);
        if (among_var == 0) {
            return false;
        }
        // ], line 184
        bra = cursor;
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 187
                // call RV, line 187
                if (!r_RV()) {
                    return false;
                }
                // delete, line 187
                slice_del();
                break;
        }
        return true;
    }

    private boolean r_residual_form() {
        int among_var;
        int v_1;
        int v_2;
        int v_3;
        // (, line 191
        // [, line 192
        ket = cursor;
        // substring, line 192
        among_var = find_among_b(a_8, 4);
        if (among_var == 0) {
            return false;
        }
        // ], line 192
        bra = cursor;
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 194
                // call RV, line 194
                if (!r_RV()) {
                    return false;
                }
                // delete, line 194
                slice_del();
                // [, line 194
                ket = cursor;
                // or, line 194
                lab0:
                do {
                    v_1 = limit - cursor;
                    lab1:
                    do {
                        // (, line 194
                        // literal, line 194
                        if (!(eq_s_b(1, "u"))) {
                            break lab1;
                        }
                        // ], line 194
                        bra = cursor;
                        // test, line 194
                        v_2 = limit - cursor;
                        // literal, line 194
                        if (!(eq_s_b(1, "g"))) {
                            break lab1;
                        }
                        cursor = limit - v_2;
                        break lab0;
                    } while (false);
                    cursor = limit - v_1;
                    // (, line 195
                    // literal, line 195
                    if (!(eq_s_b(1, "i"))) {
                        return false;
                    }
                    // ], line 195
                    bra = cursor;
                    // test, line 195
                    v_3 = limit - cursor;
                    // literal, line 195
                    if (!(eq_s_b(1, "c"))) {
                        return false;
                    }
                    cursor = limit - v_3;
                } while (false);
                // call RV, line 195
                if (!r_RV()) {
                    return false;
                }
                // delete, line 195
                slice_del();
                break;
            case 2:
                // (, line 196
                // <-, line 196
                slice_from("c");
                break;
        }
        return true;
    }

    public boolean stem() {
        int v_1;
        int v_2;
        int v_3;
        int v_4;
        int v_5;
        int v_6;
        int v_7;
        int v_8;
        int v_9;
        int v_10;
        // (, line 201
        // do, line 202
        v_1 = cursor;
        lab0:
        do {
            // call prelude, line 202
            if (!r_prelude()) {
                break lab0;
            }
        } while (false);
        cursor = v_1;
        // do, line 203
        v_2 = cursor;
        lab1:
        do {
            // call mark_regions, line 203
            if (!r_mark_regions()) {
                break lab1;
            }
        } while (false);
        cursor = v_2;
        // backwards, line 204
        limit_backward = cursor;
        cursor = limit;
        // (, line 204
        // do, line 205
        v_3 = limit - cursor;
        lab2:
        do {
            // (, line 205
            // or, line 209
            lab3:
            do {
                v_4 = limit - cursor;
                lab4:
                do {
                    // (, line 206
                    // and, line 207
                    v_5 = limit - cursor;
                    // (, line 206
                    // or, line 206
                    lab5:
                    do {
                        v_6 = limit - cursor;
                        lab6:
                        do {
                            // call standard_suffix, line 206
                            if (!r_standard_suffix()) {
                                break lab6;
                            }
                            break lab5;
                        } while (false);
                        cursor = limit - v_6;
                        // call verb_suffix, line 206
                        if (!r_verb_suffix()) {
                            break lab4;
                        }
                    } while (false);
                    cursor = limit - v_5;
                    // do, line 207
                    v_7 = limit - cursor;
                    lab7:
                    do {
                        // (, line 207
                        // [, line 207
                        ket = cursor;
                        // literal, line 207
                        if (!(eq_s_b(1, "i"))) {
                            break lab7;
                        }
                        // ], line 207
                        bra = cursor;
                        // test, line 207
                        v_8 = limit - cursor;
                        // literal, line 207
                        if (!(eq_s_b(1, "c"))) {
                            break lab7;
                        }
                        cursor = limit - v_8;
                        // call RV, line 207
                        if (!r_RV()) {
                            break lab7;
                        }
                        // delete, line 207
                        slice_del();
                    } while (false);
                    cursor = limit - v_7;
                    break lab3;
                } while (false);
                cursor = limit - v_4;
                // call residual_suffix, line 209
                if (!r_residual_suffix()) {
                    break lab2;
                }
            } while (false);
        } while (false);
        cursor = limit - v_3;
        // do, line 211
        v_9 = limit - cursor;
        lab8:
        do {
            // call residual_form, line 211
            if (!r_residual_form()) {
                break lab8;
            }
        } while (false);
        cursor = limit - v_9;
        cursor = limit_backward;                    // do, line 213
        v_10 = cursor;
        lab9:
        do {
            // call postlude, line 213
            if (!r_postlude()) {
                break lab9;
            }
        } while (false);
        cursor = v_10;
        return true;
    }

    public boolean equals(Object o) {
        return o instanceof portugueseStemmer;
    }

    public int hashCode() {
        return portugueseStemmer.class.getName().hashCode();
    }


}

