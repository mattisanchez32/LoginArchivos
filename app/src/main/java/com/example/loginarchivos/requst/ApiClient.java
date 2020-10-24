package com.example.loginarchivos.requst;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.loginarchivos.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {

    private static File carpeta, archivo;

    private static File conectar(Context context){
        carpeta = context.getFilesDir();
        archivo = new File(carpeta,"datos.dat");
        return archivo;
    }
    public static void guardar(Context context, Usuario usuario){
        archivo = conectar(context);
        try {
            FileOutputStream fo = new FileOutputStream(archivo, false);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            DataOutputStream dos = new DataOutputStream(bo);

            dos.writeLong(usuario.getDni());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getEmail());
            dos.writeUTF(usuario.getPass());

            bo.flush();
            fo.close();


        }catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_LONG).show();

        }catch (IOException io){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }
    }
    public static Usuario leer(Context context){
        archivo = conectar(context);
        Usuario usuario = null;
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);

            long dni = dis.readLong();
            String apellido = dis.readUTF();
            String nombre = dis.readUTF();
            String email = dis.readUTF();
            String pass = dis.readUTF();
            usuario = new Usuario(dni, apellido, nombre, email, pass);

            fi.close();


        }catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_LONG).show();
        }catch (IOException io){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }

        return usuario;
    }

    public static Usuario login(Context context, String mail, String password){
        Usuario usuario = null;
        archivo = conectar(context);
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);

            long dni = dis.readLong();
            String apellido = dis.readUTF();
            String nombre = dis.readUTF();
            String email = dis.readUTF();
            String pass = dis.readUTF();
            if (mail.equals(email) && password.equals(pass)) {
                usuario = new Usuario(dni, apellido, nombre, email, pass);
            }
            fi.close();

        }catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_LONG).show();
        }catch (IOException io){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }

        return usuario;
    }
}
