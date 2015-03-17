/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_5;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Adrian Portillo
 */
public class CuboHielo extends JFrame {

    SimpleUniverse universo;
    TransformGroup trans_movimiento;
    Transform3D movimiento;
    public static void main(String[] args) {
        CuboHielo x = new CuboHielo();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Cubo sobre el hielo");
        x.setSize(800, 600);
        x.setVisible(true);
        x.desplazar_cubo();
    }

    public CuboHielo() {
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
        movimiento = new Transform3D();
        
        ColorCube cubo = new ColorCube(0.1);
        Transform3D colocar = new Transform3D();
        colocar.set(new Vector3d(-.5,0,0));
        TransformGroup colocar_TG = new TransformGroup(colocar);
        colocar_TG.addChild(cubo);
        trans_movimiento = new TransformGroup(movimiento);
        trans_movimiento.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        trans_movimiento.addChild(colocar_TG);
        objRoot.addChild(trans_movimiento);
        return objRoot;
    }
    void desplazar_cubo() {
        while(true) {
            for(float f = 0.0f; f < 1.5f; f+=0.1) {
                movimiento.set(new Vector3f((-0.5f+f),0,0));
                trans_movimiento.setTransform(movimiento);
                try{
                    Thread.sleep(50);
                }catch(Exception ex) {}
            }
        }
    }
}
