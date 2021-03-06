/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Portillo
 */
public class ColorCube2_1 extends JFrame {

    SimpleUniverse universo;

    public ColorCube2_1() {
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
        ColorCube cubo_de_color = new ColorCube(0.4d);
        Transform3D rotacion = new Transform3D();
        rotacion.rotX(Math.PI / 4.0d);
        TransformGroup cuboRotandoX = new TransformGroup(rotacion);
        cuboRotandoX.addChild(cubo_de_color);
        //objRoot.addChild(cuboRotandoX);
        //ME JUEGO LO QUE SEA A QUE ESTA MAL
        Transform3D rotacionZ = new Transform3D();
        rotacionZ.rotZ(Math.PI / 4.0d);
        TransformGroup cuboRotandoZ = new TransformGroup(rotacionZ);
        cuboRotandoZ.addChild(cuboRotandoX);
        objRoot.addChild(cuboRotandoZ);
        return objRoot;
    }

    public static void main(String args[]) {
        ColorCube2_1 x = new ColorCube2_1();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("ColorCube");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
