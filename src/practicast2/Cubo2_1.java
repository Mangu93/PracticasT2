/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Portillo
 */
public class Cubo2_1 extends JFrame {

    SimpleUniverse universo;

    public Cubo2_1() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena();
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena() {
        BranchGroup objRoot = new BranchGroup();
        Appearance apariencia = new Appearance();
        ColoringAttributes colores = new ColoringAttributes(0,0,1, ColoringAttributes.FASTEST);
        apariencia.setColoringAttributes(colores);
        Box box = new Box(1f, 0.1f, 0.3f, apariencia);
        objRoot.addChild(box);
        return objRoot;
    }
     public static void main(String args[]) {
        Cubo2_1 x = new Cubo2_1();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Esfera");
        x.setSize(800, 600);
        x.setVisible(true);
    }
    
}
