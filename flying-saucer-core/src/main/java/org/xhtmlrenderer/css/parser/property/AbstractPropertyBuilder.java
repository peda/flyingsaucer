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
package org.xhtmlrenderer.css.parser.property;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.w3c.dom.css.CSSPrimitiveValueExtension;
import org.xhtmlrenderer.css.constants.CSSName;
import org.xhtmlrenderer.css.constants.IdentValue;
import org.xhtmlrenderer.css.parser.CSSParseException;
import org.xhtmlrenderer.css.parser.PropertyValue;
import org.xhtmlrenderer.css.sheet.PropertyDeclaration;

public abstract class AbstractPropertyBuilder implements PropertyBuilder {
    public List buildDeclarations(CSSName cssName, List values, int origin, boolean important) {
        return buildDeclarations(cssName, values, origin, important, true);
    }
    
    protected void checkValueCount(CSSName cssName, int expected, int found) {
        if (expected != found) {
            throw new CSSParseException("Found " + found + " value(s) for " +
                    cssName + " when " + expected + " value(s) were expected", -1);
        }
    }
    
    protected void checkValueCount(CSSName cssName, int min, int max, int found) {
        if (! (found >= min && found <= max)) {
            throw new CSSParseException("Found " + found + " value(s) for " +
                    cssName + " when between " + min + " and " + max + " value(s) were expected", -1);
        }
    }
    
    protected void checkIdentType(CSSName cssName, CSSPrimitiveValueExtension value) {
        if (value.getPrimitiveType() != CSSPrimitiveValueExtension.CSS_IDENT) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier", -1);
        }
    }
    
    protected void checkIdentOrURIType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT && type != CSSPrimitiveValueExtension.CSS_URI) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or a URI", -1);
        }
    }
    
    protected void checkIdentOrColorType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT && type != CSSPrimitiveValueExtension.CSS_RGBCOLOR) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or a color", -1);
        }
    }  
    
    protected void checkIdentOrIntegerType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if ((type != CSSPrimitiveValueExtension.CSS_IDENT &&
                type != CSSPrimitiveValueExtension.CSS_NUMBER) ||
            (type == CSSPrimitiveValueExtension.CSS_NUMBER &&
                    (int)value.getFloatValue(CSSPrimitiveValueExtension.CSS_NUMBER) !=
                        Math.round(value.getFloatValue(CSSPrimitiveValueExtension.CSS_NUMBER)))) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or an integer", -1);
        }
    }
    
    protected void checkInteger(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_NUMBER ||
                (type == CSSPrimitiveValueExtension.CSS_NUMBER &&
                    (int)value.getFloatValue(CSSPrimitiveValueExtension.CSS_NUMBER) !=
                        Math.round(value.getFloatValue(CSSPrimitiveValueExtension.CSS_NUMBER)))) {
            throw new CSSParseException("Value for " + cssName + " must be an integer", -1);
        }
    }
    
    protected void checkIdentOrLengthType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT && ! isLength(value)) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or a length", -1);
        }
    }
    
    protected void checkIdentOrNumberType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT && type != CSSPrimitiveValueExtension.CSS_NUMBER) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or a length", -1);
        }
    }
    
    protected void checkIdentLengthOrPercentType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT && ! isLength(value) && type != CSSPrimitiveValueExtension.CSS_PERCENTAGE) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier, length, or percentage", -1);
        }
    }
    
    protected void checkLengthOrPercentType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (! isLength(value) && type != CSSPrimitiveValueExtension.CSS_PERCENTAGE) {
            throw new CSSParseException("Value for " + cssName + " must be a length or percentage", -1);
        }
    }
    
    protected void checkLengthType(CSSName cssName, CSSPrimitiveValueExtension value) {
        if (! isLength(value)) {
            throw new CSSParseException("Value for " + cssName + " must be a length", -1);
        }
    }
    
    protected void checkNumberType(CSSName cssName, CSSPrimitiveValueExtension value) {
        if (value.getPrimitiveType() != CSSPrimitiveValueExtension.CSS_NUMBER) {
            throw new CSSParseException("Value for " + cssName + " must be a number", -1);
        }
    }
    
    protected void checkStringType(CSSName cssName, CSSPrimitiveValueExtension value) {
        if (value.getPrimitiveType() != CSSPrimitiveValueExtension.CSS_STRING) {
            throw new CSSParseException("Value for " + cssName + " must be a string", -1);
        }
    }
    
    protected void checkIdentOrString(CSSName cssName, CSSPrimitiveValueExtension value) {
        short type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_STRING && type != CSSPrimitiveValueExtension.CSS_IDENT) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier or string", -1);
        }
    }
    
    protected void checkIdentLengthNumberOrPercentType(CSSName cssName, CSSPrimitiveValueExtension value) {
        int type = value.getPrimitiveType();
        if (type != CSSPrimitiveValueExtension.CSS_IDENT &&
                ! isLength(value) && 
                type != CSSPrimitiveValueExtension.CSS_PERCENTAGE &&
                type != CSSPrimitiveValueExtension.CSS_NUMBER) {
            throw new CSSParseException("Value for " + cssName + " must be an identifier, length, or percentage", -1);
        }
    }
    
    protected boolean isLength(CSSPrimitiveValueExtension value) {
        int unit = value.getPrimitiveType();
        return unit == CSSPrimitiveValueExtension.CSS_EMS || unit == CSSPrimitiveValueExtension.CSS_EXS
                || unit == CSSPrimitiveValueExtension.CSS_EHS
                || unit == CSSPrimitiveValueExtension.CSS_PX || unit == CSSPrimitiveValueExtension.CSS_IN
                || unit == CSSPrimitiveValueExtension.CSS_CM || unit == CSSPrimitiveValueExtension.CSS_MM
                || unit == CSSPrimitiveValueExtension.CSS_PT || unit == CSSPrimitiveValueExtension.CSS_PC
                || (unit == CSSPrimitiveValueExtension.CSS_NUMBER && value.getFloatValue(CSSPrimitiveValueExtension.CSS_IN) == 0.0f);
    }
    
    protected void checkValidity(CSSName cssName, BitSet validValues, IdentValue value) {
        if (! validValues.get(value.FS_ID)) {
            throw new CSSParseException("Ident " + value + " is an invalid or unsupported value for " + cssName, -1);
        }
    }
    
    protected IdentValue checkIdent(CSSName cssName, CSSPrimitiveValueExtension value) {
        IdentValue result = IdentValue.valueOf(value.getStringValue());
        if (result == null) {
            throw new CSSParseException("Value " + value.getStringValue() + " is not a recognized identifier", -1);
        }
        ((PropertyValue)value).setIdentValue(result);
        return result;
    }
    
    protected PropertyDeclaration copyOf(PropertyDeclaration decl, CSSName newName) {
        return new PropertyDeclaration(newName, decl.getValue(), decl.isImportant(), decl.getOrigin());
    }
    
    protected void checkInheritAllowed(CSSPrimitiveValueExtension value, boolean inheritAllowed) {
        if (value.getCssValueType() == CSSPrimitiveValueExtension.CSS_INHERIT && ! inheritAllowed) {
            throw new CSSParseException("Invalid use of inherit", -1);
        }
    }

    protected List checkInheritAll(CSSName[] all, List values, int origin, boolean important, boolean inheritAllowed) {
        if (values.size() == 1) {
            CSSPrimitiveValueExtension value = (CSSPrimitiveValueExtension)values.get(0);
            checkInheritAllowed(value, inheritAllowed);
            if (value.getCssValueType() == CSSPrimitiveValueExtension.CSS_INHERIT) {
                List result = new ArrayList(all.length);
                for (int i = 0; i < all.length; i++) {
                    result.add(
                            new PropertyDeclaration(all[i], value, important, origin));
                }
                return result;
            }
        }
        
        return null;
    }    
}