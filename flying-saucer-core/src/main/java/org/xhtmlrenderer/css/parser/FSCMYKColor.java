/*
 * {{{ header & license
 * Copyright (c) 2007 Wisconsin Court System
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.xhtmlrenderer.css.parser;

public class FSCMYKColor implements FSColor {
    private final float _cyan;
    private final float _magenta;
    private final float _yellow;
    private final float _black;
    private final float _alpha;

    public FSCMYKColor(float c, float m, float y, float k) {
        this(c, m, y, k, 1);
    }

    public FSCMYKColor(float c, float m, float y, float k, float a) {
        if (c < 0 || c > 1) {
            throw new IllegalArgumentException();
        }
        if (m < 0 || m > 1) {
            throw new IllegalArgumentException();
        }
        if (y < 0 || y > 1) {
            throw new IllegalArgumentException();
        }
        if (k < 0 || k > 1) {
            throw new IllegalArgumentException();
        }
        if (a < 0 || a > 1) {
            throw new IllegalArgumentException();
        }
        _cyan = c;
        _magenta = m;
        _yellow = y;
        _black = k;
        _alpha = a;
    }

    public float getCyan() {
        return _cyan;
    }

    public float getMagenta() {
        return _magenta;
    }

    public float getYellow() {
        return _yellow;
    }

    public float getBlack() {
        return _black;
    }

    public float getAlpha() {
        return _alpha;
    }

    public String toString() {
        return "cmyka(" + _cyan + ", " + _magenta + ", " + _yellow + ", " + _black + ", " + _alpha + ")";
    }

    public FSColor lightenColor() {
        return new FSCMYKColor(_cyan * 0.8f, _magenta * 0.8f, _yellow * 0.8f, _black, _alpha);
    }

    public FSColor darkenColor() {
        return new FSCMYKColor(
                Math.min(1.0f, _cyan / 0.8f), Math.min(1.0f, _magenta / 0.8f),
                Math.min(1.0f, _yellow / 0.8f), _black, _alpha);
    }
}
