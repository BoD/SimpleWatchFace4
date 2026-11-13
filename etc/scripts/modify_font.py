import math
from fontTools.ttLib import TTFont
from fontTools.pens.ttGlyphPen import TTGlyphPen
from fontTools.pens.transformPen import TransformPen

def modify_font(input_path, output_path, angle_degrees, spacing_adjust):
    font = TTFont(input_path)
    glyph_set = font.getGlyphSet()
    glyf_table = font['glyf']
    hmtx_table = font['hmtx']

    # Calculate the horizontal shear factor from the angle
    skew_factor = math.tan(math.radians(angle_degrees))
    matrix = (1, 0, skew_factor, 1, 0, 0)

    for glyph_name in font.getGlyphOrder():
        glyph = glyf_table[glyph_name]

        # 1. Skew non-empty glyphs
        if glyph.numberOfContours > 0:
            pen = TTGlyphPen(glyph_set)
            t_pen = TransformPen(pen, matrix)
            glyph_set[glyph_name].draw(t_pen)
            glyf_table[glyph_name] = pen.glyph()

        # 2. Adjust letter spacing (advance width)
        if spacing_adjust != 0:
            advance_width, lsb = hmtx_table[glyph_name]
            # Ensure advance width never drops below 0
            new_width = max(0, advance_width + spacing_adjust)
            hmtx_table[glyph_name] = (new_width, lsb)

    font.save(output_path)


import sys
# modify_font(sys.argv[1], sys.argv[2], float(sys.argv[3]), int(sys.argv[4]))
modify_font(sys.argv[1], sys.argv[2], 6, -60)
