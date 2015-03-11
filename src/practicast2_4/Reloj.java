/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_4;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import practicast2_3.EscenaB;

/**
 *
 * @author Adrian Portillo
 */
public class Reloj extends JFrame {

    Color3f white = new Color3f(1f, 1f, 1f);
    float alturaCilindros = 0.5f;
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    SimpleUniverse universo;
    float anguloAntebrazo = 45f;
    TransformGroup preparaAntebrazoParaRotableTG;
    TransformGroup rotaAntebrazoTG;

    public Reloj() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena(anguloAntebrazo);
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena(float anguloAntebrazo) {
        BranchGroup objRoot = new BranchGroup();
        Material mat = new Material();
        mat.setDiffuseColor(new Color3f(1,1,0));  //color cuando hay luz direccional
        mat.setSpecularColor(new Color3f(1f, 1f, 0f));  //color amarillo para el reflejo de luz
        mat.setShininess(128f); //brillo máximo de reflejo de luz
        Appearance amarillo = new Appearance();
        amarillo.setMaterial(mat);
        Appearance verde = new Appearance();
        Material mat2 = new Material();
        mat2.setDiffuseColor(new Color3f(0, 1, 0));  //color cuando hay luz direccional
        mat2.setSpecularColor(new Color3f(1f, 1f, 0f));  //color amarillo para el reflejo de luz
        mat2.setShininess(128f); //brillo máximo de reflejo de luz
        verde.setMaterial(mat2);
        Cylinder cilindro1 = new Cylinder(0.05f, alturaCilindros, amarillo);
        Cylinder cilindro2 = new Cylinder(0.05f, alturaCilindros, verde);
        Transform3D preparaAntebrazoParaRotable = new Transform3D();
        Transform3D rotaAntebrazo = new Transform3D();
        Transform3D posicionaAntebrazo = new Transform3D();

        posicionaAntebrazo.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        rotaAntebrazo.rotZ(-anguloAntebrazo);
        preparaAntebrazoParaRotable.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        TransformGroup posicionaAntebrazoTG = new TransformGroup(posicionaAntebrazo);
        rotaAntebrazoTG = new TransformGroup(rotaAntebrazo);
        //TransformGroup preparaAntebrazoParaRotableTG = new TransformGroup(preparaAntebrazoParaRotable);
        preparaAntebrazoParaRotableTG = new TransformGroup(preparaAntebrazoParaRotable);
        objRoot.addChild(cilindro1);
        objRoot.addChild(posicionaAntebrazoTG);
        posicionaAntebrazoTG.addChild(rotaAntebrazoTG);
        rotaAntebrazoTG.addChild(preparaAntebrazoParaRotableTG);
        rotaAntebrazoTG.addChild(cilindro2);
        rotaAntebrazoTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        /*
         preparaAntebrazoParaRotableTG.addChild(cilindro2);
         preparaAntebrazoParaRotableTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);*/
        //Luz
        DirectionalLight LuzDireccional = new DirectionalLight(new Color3f(1f, 1f, 1f), new Vector3f(1f, 0f, -1f));
        BoundingSphere limites = new BoundingSphere(new Point3d(-5, 0, 5), 100.0); //Localizacion de fuente/paso de luz
        LuzDireccional.setInfluencingBounds(limites);
        objRoot.addChild(LuzDireccional);
        return objRoot;
    }

    public static void main(String args[]) {
        Reloj x = new Reloj();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Reloj");
        x.setSize(800, 600);
        x.setVisible(true);
        Transform3D actual = new Transform3D();
        Transform3D rotacion = new Transform3D();
        float this_angulo = 0.01f;
        while (true) {
            try {
                x.rotaAntebrazoTG.getTransform(actual);
                rotacion.rotZ(this_angulo);
                actual.mul(rotacion);
                x.rotaAntebrazoTG.setTransform(actual);

                Thread.sleep(10);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());

            }
        }
    }
}
