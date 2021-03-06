package com.reanstudio.imagefilter.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by yahyamukhlis on 4/12/16.
 */
public class FlatColoredSquare {
    // Our vertices
    private float vertices[] = {
        -1.0f, 1.0f, 0.0f, // 0, Top Left
        -1.0f, -1.0f, 0.0f, // 1, Bottom Left
        1.0f, -1.0f, 0.0f, // 2, Bottom Right
        1.0f, 1.0f, 0.0f, // 3, Top Right
    };

    // The order we like to connect them
    private short[] indices = { 0, 1, 2, 0, 2, 3};

    // Our vertex buffer
    private FloatBuffer vertexBuffer;

    // Our index buffer
    private ShortBuffer indexBuffer;

    public FlatColoredSquare() {
        // a float is 4 bytes, therefore we multiply the number if vertices with 4
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply the number if vertices with 2
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public void draw(GL10 gl10) {
        gl10.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        // Counter-clockwise winding
        gl10.glFrontFace(GL10.GL_CCW);
        // Enable face culling
        gl10.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling
        gl10.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during rendering
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex coordinates to use when rendering
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl10.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer
        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling
        gl10.glDisable(GL10.GL_CULL_FACE);
    }
}
