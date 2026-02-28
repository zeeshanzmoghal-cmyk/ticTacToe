package com.zzm.imageRenderAction;

import javax.swing.ImageIcon;
import java.util.Objects;

public class imageRenderer {
    public static ImageIcon load(String path) {
        return new ImageIcon(
                Objects.requireNonNull(
                        imageRenderer.class.getResource(path)
                )
        );
    }
}
