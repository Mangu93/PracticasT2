/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_2;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import practicast2.ColorCube2_1;

/**
 *
 * @author Adrian Portillo
 */
public class Elefantes extends JFrame {

    SimpleUniverse universo;

    public Elefantes() {
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
        BranchGroup final_bg = new BranchGroup();
        //begin Apariencia
        Appearance apariencia = new Appearance();
        Color3f color_material = new Color3f(1f, 1f, 0f);
        Material material = new Material();
        material.setDiffuseColor(color_material);
        material.setSpecularColor(1f, 1f, 0f);
        apariencia.setMaterial(material);
        //begin Box and Sphere
        Box box = new Box(0.3f, 0.2f, 0.1f, apariencia);
        Sphere esfera = new Sphere(0.5f, apariencia);
        //begin Transform
        Transform3D colocar = new Transform3D();
        Transform3D rotacion = new Transform3D();
        Transform3D colocar_box = new Transform3D();
        colocar_box.set(new Vector3f(0f,-0.2f,0f));
        colocar.set(new Vector3f(0f, 0.5f, 0.0f));
        TransformGroup colocar_esfera = new TransformGroup(colocar);
        colocar_esfera.addChild(esfera);
        //sphere in position
        //begin lights
        BoundingSphere limites = new BoundingSphere(new Point3d(-5, 0, 5), 100.0); //Localizacion de fuente/paso de luz
        AmbientLight LuzAmbiente = new AmbientLight(new Color3f(0.2f, 0.5f, 0.8f));
        LuzAmbiente.setInfluencingBounds(limites);
        objRoot.addChild(LuzAmbiente);
        //Debe añadirse a otro TG
        TransformGroup colocar_box_tg = new TransformGroup(colocar_box);
        colocar_box_tg.addChild(box);
        objRoot.addChild(colocar_box_tg);
        //objRoot.addChild(box);
        
        Transform3D deformar = new Transform3D();  
        deformar.setScale(new Vector3d(-0.5,0.5,1));
        TransformGroup reducir_esfera = new TransformGroup(deformar);
        reducir_esfera.addChild(colocar_esfera);
        //Debe añadirse a otro BG
        objRoot.addChild(reducir_esfera);
        //añadir rotacion final
        /*rotacion.rotY(Math.PI / 4.0d);
        TransformGroup rotacion_grupal = new TransformGroup(rotacion);
        rotacion_grupal.addChild(objRoot);
        final_bg.addChild(rotacion_grupal);*/
        final_bg.addChild(objRoot);
        return final_bg;
    }

    public static void main(String args[]) {
        Elefantes x = new Elefantes();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("El cazador de elefantes");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
