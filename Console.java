package aa_misEjercicios.misUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/** En Visual Studio Code                                                   */
/** Para desplegar todo usar:     ctrl+k ctrl+j                             */
/** Para plegar todo usar:        ctrl+k ctrl+0 (el de arriba de las letras)*/
/** Para plegar regiones usar:    ctrl+k ctrl+8 (el de arriba de las letras)*/
/** Para desplegar regiones usar: ctrl+k ctrl+9 (el de arriba de las letras)*/
/** TODO: Añadir métodos para tipo Decimal y similares que faltan. */

public class Console {

  private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  // #region Utils

  /**
   * OS Independent: Utilidad para limpiar la consola en cualquier sistema
   * operativo, incluyendo Windows, Mac y Linux.
   * Utility to clear the console in any operating system, including Windows, Mac
   * and Linux.
   * 
   * @Author Manuel Rosendo Castro Iglesias
   * @Author Adaptación por ChatGPT {@link https://chat.openai.com/chat}
   *         To use it, call the console.cls() method where you need to clean the
   *         console.
   *         Para usarlo, llama al método console.cls() donde necesites limpiar la
   *         consola.
   */
  public void cls() {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) { // si el sistema operativo es Windows
      try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } catch (Exception e) {
        System.out.println("No se pudo limpiar la consola.");
      }
    } else { // en cualquier otro sistema operativo
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }

  // #endregion Utils

  // #region console_read

  /** Error message reading fractional number format, output in spanish */
  private void writeError(String formato) {
    System.out.println("ERROR DE FORMATO! " +
        "Introduzca un valor con formato " + formato + ".");
  }

  public boolean readBoolean(String title) {
    boolean inputLogica = true;
    boolean ok = false;
    do {
      String input = this.readString(title);
      if (input.equalsIgnoreCase("true") ||
          input.equalsIgnoreCase("false")) {
        inputLogica = Boolean.valueOf(input).booleanValue();
        ok = true;
      } else {
        this.writeError("logico");
      }
    } while (!ok);
    return inputLogica;
  }

  public char readChar(String title) {
    char caracter = ' ';
    boolean ok = false;
    do {
      String input = this.readString(title);
      if (input.length() != 1) {
        this.writeError("caracter");
      } else {
        caracter = input.charAt(0);
        ok = true;
      }
    } while (!ok);
    return caracter;
  }

  public String readString(String title) {
    String input = null;
    boolean ok = false;
    do {
      this.write(title);
      try {
        input = bufferedReader.readLine();
        ok = true;
      } catch (Exception e) {
        this.writeError("de cadena de caracteres");
      }
    } while (!ok);
    return input;
  }

  // #region Tipos numéricos

  public byte readByte(String title) {
    byte input = 0;
    boolean ok = false;
    do {
      try {
        input = Byte.parseByte(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("entero");
      }
    } while (!ok);
    return input;
  }

  public short readShort(String title) {
    short input = 0;
    boolean ok = false;
    do {
      try {
        input = Short.parseShort(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("entero");
      }
    } while (!ok);
    return input;
  }

  public int readInt(String title) {
    int input = 0;
    boolean ok = false;
    do {
      try {
        input = Integer.parseInt(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("entero");
      }
    } while (!ok);
    return input;
  }

  public long readLong(String title) {
    long input = 0;
    boolean ok = false;
    do {
      try {
        input = Long.parseLong(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("entero");
      }
    } while (!ok);
    return input;
  }

  public float readFloat(String title) {
    float input = 0;
    boolean ok = false;
    do {
      try {
        input = Float.parseFloat(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("real");
      }
    } while (!ok);
    return input;
  }

  public double readDouble(String title) {
    double input = 0.0;
    boolean ok = false;
    do {
      try {
        input = Double.parseDouble(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("real");
      }
    } while (!ok);
    return input;
  }

  public BigDecimal readBigDecimal(String title) {
    BigDecimal input = null;
    boolean ok = false;
    do {
      try {
        input = new BigDecimal(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("decimal");
      }
    } while (!ok);
    return input;
  }

  public BigInteger readBigInteger(String title) {
    BigInteger input = null;
    boolean ok = false;
    do {
      try {
        input = new BigInteger(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("entero grande");
      }
    } while (!ok);
    return input;
  }
  // #endregion Tipos numéricos

  // #region Tipos de fecha y hora
  public LocalDate readDate(String title) {
    LocalDate inputFecha = null;
    boolean ok = false;
    do {
      try {
        inputFecha = LocalDate.parse(this.readString(title));
        ok = true;
      } catch (DateTimeParseException e) {
        this.writeError("fecha");
      }
    } while (!ok);
    return inputFecha;
  }

  public LocalDateTime readDateTime(String title) {
    LocalDateTime inputFechaHora = null;
    boolean ok = false;
    do {
      try {
        inputFechaHora = LocalDateTime.parse(this.readString(title));
        ok = true;
      } catch (DateTimeParseException e) {
        this.writeError("fecha y hora");
      }
    } while (!ok);
    return inputFechaHora;
  }

  public LocalTime readTime(String title) {
    LocalTime inputHora = null;
    boolean ok = false;
    do {
      try {
        inputHora = LocalTime.parse(this.readString(title));
        ok = true;
      } catch (DateTimeParseException e) {
        this.writeError("hora");
      }
    } while (!ok);
    return inputHora;
  }

  public LocalDate readLocalDate(String title) {
    LocalDate input = null;
    boolean ok = false;
    do {
      try {
        input = LocalDate.parse(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("fecha");
      }
    } while (!ok);
    return input;
  }

  public LocalDateTime readLocalDateTime(String title) {
    LocalDateTime input = null;
    boolean ok = false;
    do {
      try {
        input = LocalDateTime.parse(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("fecha y hora");
      }
    } while (!ok);
    return input;
  }

  public LocalTime readLocalTime(String title) {
    LocalTime input = null;
    boolean ok = false;
    do {
      try {
        input = LocalTime.parse(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("hora");
      }
    } while (!ok);
    return input;
  }

  public Duration readDuration(String title) {
    Duration input = null;
    boolean ok = false;
    do {
      try {
        input = Duration.parse(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("duración");
      }
    } while (!ok);
    return input;
  }

  public Period readPeriod(String title) {
    Period input = null;
    boolean ok = false;
    do {
      try {
        input = Period.parse(this.readString(title));
        ok = true;
      } catch (Exception e) {
        this.writeError("período");
      }
    } while (!ok);
    return input;
  }

  // #endregion Tipos de fecha y hora

  // #region Tipos de lista
  public <T extends Enum<T>> T readEnum(Class<T> enumClass, String title) {
    T input = null;
    boolean ok = false;
    do {
      try {
        input = Enum.valueOf(enumClass, this.readString(title).toUpperCase());
        ok = true;
      } catch (Exception e) {
        this.writeError("valor de enumeración " + enumClass.getSimpleName());
      }
    } while (!ok);
    return input;
  }

  public byte[] readByteArray(String title) {
    byte[] input = null;
    boolean ok = false;
    do {
      try {
        input = this.readString(title).getBytes();
        ok = true;
      } catch (Exception e) {
        this.writeError("conjunto de bytes");
      }
    } while (!ok);
    return input;
  }

  public List<Integer> readIntList(String title) {
    List<Integer> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readInt(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<Double> readDoubleList(String title) {
    List<Double> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readDouble(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<Float> readFloatList(String title) {
    List<Float> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readFloat(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<Long> readLongList(String title) {
    List<Long> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readLong(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<BigDecimal> readBigDecimalList(String title) {
    List<BigDecimal> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readBigDecimal(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<BigInteger> readBigIntegerList(String title) {
    List<BigInteger> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readBigInteger(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<LocalDate> readLocalDateList(String title) {
    List<LocalDate> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readLocalDate(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<LocalDateTime> readLocalDateTimeList(String title) {
    List<LocalDateTime> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readLocalDateTime(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<LocalTime> readLocalTimeList(String title) {
    List<LocalTime> input = new ArrayList<>();
    int count = this.readInt(title + " (cantidad de elementos)");
    for (int i = 0; i < count; i++) {
      input.add(this.readLocalTime(title + " (elemento " + (i + 1) + ")"));
    }
    return input;
  }

  public List<Duration> readDurations(String title) {
    List<Duration> inputDuraciones = new ArrayList<>();
    boolean seguir = true;
    do {
      String input = this.readString(title);
      if (input.equalsIgnoreCase("")) {
        seguir = false;
      } else {
        try {
          Duration duracion = Duration.parse(input);
          inputDuraciones.add(duracion);
        } catch (DateTimeParseException e) {
          this.writeError("duracion");
        }
      }
    } while (seguir);
    return inputDuraciones;
  }

  // #endregion Tipos de lista

  // #endregion console_read

  // #region console_write

  // #region return console_write_string

  public void write(boolean value) {
    System.out.print(value);
  }

  public void write(char value) {
    System.out.print(value);
  }

  public void write(String value) {
    System.out.print(value);
  }

  public void write(byte value) {
    System.out.print(value);
  }

  public void write(short value) {
    System.out.print(value);
  }

  public void write(int value) {
    System.out.print(value);
  }

  public void write(long value) {
    System.out.print(value);
  }

  public void write(float value) {
    System.out.print(value);
  }

  public void write(double value) {
    System.out.print(value);
  }

  // #endregion return console_write_string

  // #region return console_write_arrays_strings

  public void write(boolean[] values) {
    for (boolean value : values) {
      this.write(value + " - ");
    }
  }

  public void write(char[] values) {
    for (char value : values) {
      this.write(value + " - ");
    }
  }

  public void write(String[] values) {
    for (String value : values) {
      this.write(value);
    }
  }

  public void write(byte[] values) {
    for (byte value : values) {
      this.write(value + " - ");
    }
  }

  public void write(short[] values) {
    for (short value : values) {
      this.write(value + " - ");
    }
  }

  public void write(int[] values) {
    for (int value : values) {
      this.write(value + " - ");
    }
  }

  public void write(long[] values) {
    for (long value : values) {
      this.write(value + " - ");
    }
  }

  public void write(float[] values) {
    for (float value : values) {
      this.write(value + " - ");
    }
  }

  public void write(double[] values) {
    for (double value : values) {
      this.write(value + " - ");
    }
  }

  // #endregion return console_write_arrays_strings

  // #endregion console_write

  // #region console_writeln

  /** Method override console_write */
  public void writeln() {
    this.write("\n");
  }

  // #region return console_writeln_value

  public void writeln(boolean value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(char value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(String value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(byte value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(short value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(int value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(long value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(float value) {
    this.write(value);
    this.writeln();
  }

  public void writeln(double value) {
    this.write(value);
    this.writeln();
  }

  // #endregion return console_writeln_value

  // #region return console_writeln_arrays_values

  public void writeln(boolean[] values) {
    for (boolean value : values) {
      this.writeln(value);
    }
  }

  public void writeln(char[] values) {
    for (char value : values) {
      this.writeln(value);
    }
  }

  public void writeln(String[] values) {
    for (String value : values) {
      this.writeln(value);
    }
  }

  public void writeln(byte[] values) {
    for (byte value : values) {
      this.writeln(value);
    }
  }

  public void writeln(short[] values) {
    for (short value : values) {
      this.writeln(value);
    }
  }

  public void writeln(int[] values) {
    for (int value : values) {
      this.writeln(value);
    }
  }

  public void writeln(long[] values) {
    for (long value : values) {
      this.writeln(value);
    }
  }

  public void writeln(float[] values) {
    for (float value : values) {
      this.writeln(value);
    }
  }

  public void writeln(double[] values) {
    for (double value : values) {
      this.writeln(value);
    }
  }

  // #endregion return console_writeln_arrays_values

  // #endregion console_writeln

  // #region Códigos faltantes.

  // #endregion Códigos faltantes.

}
