package com.javase.programmingcomplete.part3.primitivewrapperobjects;

import java.time.*;

public class P2PrimitiveWrappers {

    /*
    Porque usar as classes wrappers ao invés dos primitivos? As classes tem métodos que permitem realizar
    operações enquanto que os tipos primitivos são somente valores.

    Você pode construir Wrappers através do método valueOf().
    Você pode extrair os primitivos de um wrapper através do método xxxValue(), por exemplo, intValue()
    Você também pode usar a conversão direta colocando diretamente o sinal de igual, conhecido como auto-boxing e
    auto-unboxing.



     */

    public static void conversionBetweenTypes() {
        int a = 42;
        Integer b = Integer.valueOf(a);
        int c = b.intValue();
        // Usando o direct assignment (auto-boxing e auto-unboxing)
        b = a;
        c = b;
    }

    /*
    BigDecimal. Assim como String, é imutável. Cada operação de mutação do valor, na real cria uma nova instância.
    A classe BigDecimal sempre trata os valores como decimais, ao contrário de tipos primitivos que não necessariamente
    vão usar valores primitivos para realizar calculos. Além disso, BigDecimal permite criar valores com precisão
    arbitrária, ao contrário de float ou double em que a precisão é 32 ou 64 bits.
    Com BigDecimal você também pode alterar ou dizer qual o scale que deve ser usado através do método setScale(), e
    também dizer qual o arredondamento que deve ser usado (RoundingMode).
    E como estamos lidando com um objeto, usamos os métodos do objeto (add, subtract, divide, multiply, reminder) para
    realizar as operações (ao invés de usar os operadores +, -, /, *,
     */

    /*
    Method chaining. Se você for fazer várias operações, como add, multiply, setScale, etc, é possível usar o
    method chaining. Se você não precisa dos resultados intermediários, é desencorajado declarar elas e sim
    utilizar direto o method chaining.
     */

    /*
    Local Date and Time. A antiga java.util.Date basicamente representa um timestamp. Já a nova api do java.time com
    LocalDate, LocalTime e LocalDateTime pode representar somente Date ou somente Time e também situações mais especificas.
    O método .of() permite representar pontos especificos no tempo.
    Podemos aplicar algumas operações nesses objetos, como plusXXX(), minusXXX(), withXXX(), eles permitem criar um novo
    objeto baseado em uma data ou hora anterior.
    Assim como na classe String e BigDecimal, LocalDate, LocalTime e LocalDateTime são imutáveis. Toda vez que aplicamos
    um método de manipulação, ele não está manipulando, mas sim criando um nova instância com o novo valor.
     */

    /*
    Instants, Durations, and Periods. Assim como as classes anteriores, eles também são imutáveis. A antiga java.util.Date
    era mutável.
    Em adição ao LocalDate, LocalTime e LocalDateTime, temos Duration e Period que representam uma quantidade de tempo.
    E Instant que representa um timestamp. Instant é o mais próximo lógicamente da antiga java.util.Date, que também era basicamente
    um timestamp.

    Duration pode expressar a quantidade de tempo em nano segundos.

    Period pode expressar a quantidade de tempo em dias ou anos. Considere uma distância entre dois pontos no tempo. Se você quer adicionar ou subtrair um dia, isso pode ser diferente
    de adicionar ou subtrair 24 horas. Se você está considerando daylight saving time (DST) (horário de verão), vocẽ pode
    ter dias com 23 ou 25 horas nessa situação.

    Já Duration considera somente a quantidade de nano segundos. Ela não se importa com o seu calendário ou se onde você está
    tem horário de verão.

    Para criar instâncias de Duration, Period e Instant, você pode usar os métodos now(), ofXXX(), plusXXX(), minusXXX(),
    withXXX(), getXXX()
     */

    public static void instantDurationPeriods() {
        LocalDate today = LocalDate.now();
        LocalDate foolsDay = LocalDate.of(2019, Month.APRIL, 1);
        Instant timeStamp = Instant.now();
        int nanoSecondsFromLastSecond = timeStamp.getNano();

        // Calcular a distância entre duas datas.
        Period howLong = Period.between(foolsDay, today);

        // Expressar o tempo de duração de duas horas
        Duration twoHours = Duration.ofHours(2);

        // Com Duration, posso pegar a representação do tempo de duração em qualquer unidade que eu quiser.
        long seconds = twoHours.minusMinutes(15).getSeconds();

        // Com Period também consigo representar em qualquer unidade que eu desejar (dias, meses, etc)
        int days = howLong.getDays();
    }

    public static void main(String[] args) {
        conversionBetweenTypes();

    }



}
