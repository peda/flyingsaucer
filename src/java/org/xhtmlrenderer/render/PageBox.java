/*
 * {{{ header & license
 * Copyright (c) 2005 Wisconsin Court System
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
package org.xhtmlrenderer.render;

import java.awt.Rectangle;

import org.xhtmlrenderer.css.constants.CSSName;
import org.xhtmlrenderer.css.style.CalculatedStyle;
import org.xhtmlrenderer.css.style.CssContext;

public class PageBox {
    private Style _style;
    
    private int _top;
    private int _bottom;
    
    private int _paintingTop;
    private int _paintingBottom;
    
    public int getWidth(CssContext cssCtx) {
        return (int)getStyle().getCalculatedStyle().getFloatPropertyProportionalTo(
                CSSName.FS_PAGE_WIDTH, 0, cssCtx);
    }
    
    public int getHeight(CssContext cssCtx) {
        return (int)getStyle().getCalculatedStyle().getFloatPropertyProportionalTo(
                CSSName.FS_PAGE_HEIGHT, 0, cssCtx);
    }
    
    public int getContentHeight(CssContext cssCtx) {
        return getHeight(cssCtx) 
            - getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.TOP)
            - getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.BOTTOM);
    }
    
    public int getContentWidth(CssContext cssCtx) {
        return getWidth(cssCtx) 
            - getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.LEFT)
            - getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.RIGHT);
    }
    
    public Style getStyle() {
        return _style;
    }

    public void setStyle(Style style) {
        _style = style;
    }

    public int getBottom() {
        return _bottom;
    }

    public int getTop() {
        return _top;
    }
    
    public void setTopAndBottom(CssContext cssCtx, int top) {
        _top = top;
        _bottom = top + getContentHeight(cssCtx);
    }

    public int getPaintingBottom() {
        return _paintingBottom;
    }

    public void setPaintingBottom(int paintingBottom) {
        _paintingBottom = paintingBottom;
    }

    public int getPaintingTop() {
        return _paintingTop;
    }

    public void setPaintingTop(int paintingTop) {
        _paintingTop = paintingTop;
    }
    
    public Rectangle getOverallPaintingBounds(CssContext cssCtx, int additionalClearance) {
        return new Rectangle(
                0, getPaintingTop(),
                getWidth(cssCtx) + additionalClearance*2, getPaintingBottom()-getPaintingTop());
    }
    
    public Rectangle getContentClippingBounds(CssContext cssCtx, int additionalClearance) {
        Rectangle result = new Rectangle(
                additionalClearance + 
                    getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.LEFT),
                getPaintingTop() + additionalClearance +
                    getStyle().getMarginBorderPadding(cssCtx, CalculatedStyle.TOP),
                getContentWidth(cssCtx),
                getContentHeight(cssCtx));

        // HACK Work through why this is necessary
        result.translate(-1, 0);
        result.width += 1;
        
        return result;
    }
}