package com.java.stuff.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.java.stuff.util.Tools;

public class SerializationTest
{

	private static final String BYTES = " bytes";
	private static final String SERIALIZATION = "serialization";
	private static final String EXTERNALIZATION = "externalization";
	private static final String DIRECT_EXTERNALIZATION = "direct externalization";
	private static final String COLONS = ":";
	private static final String EQUALS = " = ";
	private static final String FILE_SIZE_FOR = "File size for ";
	private static final String PATH = "C://Temp//ser";
	private static final String FOR_DE = "For de-";

	public static void main(String[] args) throws IOException {
		int times = 100;
		double[] generatedArray = doubleArrayGenerator(100000);

		testForSimpleObjects(times, generatedArray);
	}

	private static void testForSimpleObjects(int times, double[] generatedArray) throws IOException {
		TestObject serializableObject = new TestObject(13l, true, generatedArray, "text");
		ExternalizableObject externalizableObject = new ExternalizableObject(13l, true, generatedArray, "text");
		DirectlyExternalizableObject directlyExternalizableObject = new DirectlyExternalizableObject(13l, true, generatedArray, "text");
		
		String path = PATH + "//serializableObject";
		System.out.println(SERIALIZATION + COLONS);
		serializeLoop(times, path, serializableObject);

		System.out.println(FOR_DE + SERIALIZATION + COLONS);
		TestObject deserialized = deserializeLoop(times, path, TestObject.class);
		long serializedSize = Files.size(Paths.get(path));

		path = PATH + "//externalizableObject";
		System.out.println(EXTERNALIZATION + COLONS);
		serializeLoop(times, path, externalizableObject);

		System.out.println(FOR_DE + EXTERNALIZATION + COLONS);
		ExternalizableObject deserializedExt = deserializeLoop(times, path, ExternalizableObject.class);
		long externalizedSize = Files.size(Paths.get(path));

		path = PATH + "//directlyExternalizableObject";
		System.out.println(DIRECT_EXTERNALIZATION + COLONS);
		serializeLoop(times, path, directlyExternalizableObject);

		System.out.println(FOR_DE + DIRECT_EXTERNALIZATION + COLONS);
		DirectlyExternalizableObject deserializedDirectlyExt = deserializeLoop(times, path, DirectlyExternalizableObject.class);
		long directyExternalizedSize = Files.size(Paths.get(path));

		System.out
				.println("\nObjects are equal: "
						+ (serializableObject.equals(deserialized) || deserialized.equals(deserializedExt) || deserialized
								.equals(deserializedDirectlyExt)));

		System.out.println(FILE_SIZE_FOR + SERIALIZATION + EQUALS + serializedSize + BYTES);
		System.out.println(FILE_SIZE_FOR + EXTERNALIZATION + EQUALS + externalizedSize + BYTES);
		System.out.println(FILE_SIZE_FOR + DIRECT_EXTERNALIZATION + EQUALS + directyExternalizedSize + BYTES);
	}

	private static <T> void serializeLoop(int times, String path, Object object) {
		long startTime = System.nanoTime();

		for (int i = 0; i < times; i++)
			serializeObject(path, object);
		Tools.printAndResetTime(startTime);
	}

	private static <T> T deserializeLoop(int times, String path, Class<T> type) throws IOException {
		long startTime = System.nanoTime();
		T deserialized = null;
		for (int i = 0; i < times; i++)
			deserialized = deserializeObject(path, type);
		Tools.printAndResetTime(startTime);

		return deserialized;
	}

	private static <T> void serializeObject(String path, T testObject) {
		try (FileOutputStream fo = new FileOutputStream(path); ObjectOutputStream os = new ObjectOutputStream(fo)) {
			os.writeObject(testObject);
			os.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T deserializeObject(String path, Class<T> type) {
		T testObject = null;
		try (FileInputStream fi = new FileInputStream(path); ObjectInputStream is = new ObjectInputStream(fi)) {
			testObject = (T) is.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return testObject;
	}

	private static double[] doubleArrayGenerator(int n) {
		double[] array = new double[n];
		Random r = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(100);
		}
		return array;
	}

}
