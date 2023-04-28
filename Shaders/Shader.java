package Shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.nio.*;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;

public class Shader {
	int shader;

	public Shader(String vertexPath, String fragmentPath, GL2 gl) {
		BufferedReader brv;
		BufferedReader brf;

		try {
			brv = new BufferedReader(new FileReader("Shaders/basic_texture.vs"));
			brf = new BufferedReader(new FileReader("Shaders/basic_texture.fs"));

			int v = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
			int f = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

			String vsrc = "";
			String line;
			while ((line = brv.readLine()) != null) {
				vsrc += line + "\n";
			}
			gl.glShaderSource(v, 1, new String[] { vsrc }, (int[]) null, 0);
			gl.glCompileShader(v);
			checkShaderLogInfo(gl, v);

			String fsrc = "";
			while ((line = brf.readLine()) != null) {
				fsrc += line + "\n";
			}
			gl.glShaderSource(v, 1, new String[] { fsrc }, (int[]) null, 0);
			gl.glCompileShader(f);
			checkShaderLogInfo(gl, f);

			shader = gl.glCreateProgram();
			gl.glAttachShader(shader, v);
			gl.glAttachShader(shader, f);
			gl.glLinkProgram(shader);
			gl.glValidateProgram(shader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void checkShaderLogInfo(GL2 gl, int inShaderObjectID) {
		IntBuffer tReturnValue = Buffers.newDirectIntBuffer(1);
		gl.glGetObjectParameterivARB(inShaderObjectID, GL2.GL_OBJECT_INFO_LOG_LENGTH_ARB, tReturnValue);
		int tLogLength = tReturnValue.get();
		if (tLogLength <= 1) {
			return;
		}
		ByteBuffer tShaderLog = Buffers.newDirectByteBuffer(tLogLength);
		tReturnValue.flip();
		gl.glGetInfoLogARB(inShaderObjectID, tLogLength, tReturnValue, tShaderLog);
		byte[] tShaderLogBytes = new byte[tLogLength];
		tShaderLog.get(tShaderLogBytes);
		String tShaderValidationLog = new String(tShaderLogBytes);
		StringReader tStringReader = new StringReader(tShaderValidationLog);
		LineNumberReader tLineNumberReader = new LineNumberReader(tStringReader);
		String tCurrentLine;
		try {
			while ((tCurrentLine = tLineNumberReader.readLine()) != null) {
				if (tCurrentLine.trim().length() > 0) {
					System.out.println("GLSL VALIDATION: " + tCurrentLine.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return shader;
	}

	public void use(GL2 gl) {
		gl.glUseProgram(shader);
	}

	public void setBool(String name, boolean value, GL2 gl) {
		gl.glUniform1i(gl.glGetUniformLocation(shader, name), value ? 1 : 0);
	}

	public void setInt(String name, int value, GL2 gl) {
		gl.glUniform1i(gl.glGetUniformLocation(shader, name), value);
	}

	public void setFloat(String name, float value, GL2 gl) {
		gl.glUniform1f(gl.glGetUniformLocation(shader, name), value);
	}
}