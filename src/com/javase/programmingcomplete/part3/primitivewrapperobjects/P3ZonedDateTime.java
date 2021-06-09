package com.javase.programmingcomplete.part3.primitivewrapperobjects;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class P3ZonedDateTime {

    /*
    ZonedDateTime.
    Primeiro você precisa descobrir qual o timezone que você precisa usar. Para isso, é usado a classe ZoneId.
    Você pode especificar o ZoneId de diversas formas. Você pode usar ZoneId como offsets UTC. Você pode usar
    ZoneId como offsets GMT. Você pode usar ZoneId pelo nome.
     */

    public static void zonedDateTime() {
        ZoneId london = ZoneId.of("Europe/London");
        ZoneId la = ZoneId.of("America/Los_Angeles");
        /* Cria um LocalDateTime em um horário especifico. */
        LocalDateTime someTime = LocalDateTime.of(2019, Month.APRIL, 1, 07, 14);
        /* Agora crio o horário em London, ou seja, qual seria esse horário com o timezone de london. (horário local de london) */
        ZonedDateTime londonTime = ZonedDateTime.of(someTime, london);
        /* withZoneSameInstant me diz qual é esse horário de london lá em LA. Ele considera o calendário, horário de
        * verão, international time/date boundaries, etc. Ele já calcula isso pra nós. */
        ZonedDateTime laTime = londonTime.withZoneSameInstant(la);

        /* Setando os ZoneIds */
        ZoneId.of("America/Los_Angeles"); // Usando o nome.
        ZoneId.of("GMT+2"); // Usando offset GMT.
        ZoneId.of("UTC-05:00"); // Usando offsets UTC.
        ZoneId.systemDefault();
    }

    /*
    Represent Languages and Countries.
    Diferentes locais e países representam datas e números como texto de forma diferente. Por padrão, quando você lida com
    LocalDateTime, ZonedDateTime, Java vai assumir que você usa uma representção ISO calendar e ISO date, que é ano, mês,
    dia, horas, minutos, segundos. Mas se você quiser uma representação mais especifica do país ou da língua para datas e
    números, você pode fazer isso, mas precisa especificar qual país e qual língua. Para isso, usamos a classe Locale.
    Locale pode ser iniciada com somente a língua, com a língua e o país e também pode ter variações customizadas
     */
    public static void specifyingLocale() {
        // país e a língua
        Locale uk = new Locale("en", "GB"); // English Britain

        // variantes customizadas (eu tenho uma customização que gostaria de aplicar em cima da localização padrão).
        Locale ukCustom = new Locale("en", "GB", "EURO"); // English Britain Euro (Custom variant)
        Locale us = new Locale("en", "US"); // English America
        Locale fr = new Locale("fr", "FR"); // French France
        Locale cf = new Locale("fr", "CA"); // French Canada

        // Usando códigos designados para territórios. (As vezes você lida com uma região que não é um país, então você
        // especifica a região, como o Caribe por exemplo).
        Locale fr1 = new Locale("fr", "029"); // French Caribbean

        // Somente a língua.
        Locale es = new Locale("fr"); // French
        Locale current = Locale.getDefault();

        // Example constructing locale that uses Thai numbers and Buddhist calendar.
        // Você especifica a língua, o país e pode customizar em detalhes, caso alguma região use algo mais especifico.
        // Você faz isso com o método forLanguageTag.
        Locale th = Locale.forLanguageTag("th-TH-u-ca-buddhist-nu-thai");
    }
    
    /*
    Using Locale.
    Os números nesse exemplo estão representados usando BigDecimal, Double (wrapper) and int (primitive). Porque essas três?
    Para demonstrar que a abordagem é aplicada igual para esses diferentes tipos (BigDecimal, Wrappers e primitivos).
     */

    public static void usingLocaleNumbers() {

        BigDecimal price = BigDecimal.valueOf(2.99);
        Double tax = 0.2;
        int quantity = 12345;

        // Inicializamos o locale para inglês britânico.
        Locale locale = new Locale("en", "GB");

        // Qual o formato para curreny no inglês britânico?
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        // Qual o formato para porcentagem no inglês britânico?
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        // Qual o formato para números no inglês britânico?
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        // Usando esses formatters, posso chamar o método format e passar qualquer BigDecimal, wrapper, primitive, qualquer
        // valor que eu precisar. Ele retorna uma String formatada para aquele locale específico.
        String formattedPrice = currencyFormat.format(price);
        String formattedTax = percentageFormat.format(tax);
        String formattedQuantity = numberFormat.format(quantity);

        // Fazendo o caminho inverso agora, convertendo texto em valores numéricos.
        // Converte esse valor britânico (1.7 pounds) para BigDecimal. Primeiro chama o método parse. Ele recebe uma String
        // e retorna um objeto do tipo Number (classe pai de BigDecimal e de todas as outras classes wrappers de números).
        // Então você precisa converter esse Number para o tipo específico que eu preciso apresentar (BigDecimal? Double?
        // Algum tipo primitivo?)
        try {
            BigDecimal p = BigDecimal.valueOf((Double) currencyFormat.parse("£1.7"));
            Double t = (Double) percentageFormat.parse("12%");
            int q = numberFormat.parse("54,321").intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
    Usar locale com data e hora. Abordagem muito similar à usada com valores numéricos. Você usa a classe
    java.time.format.DateTimeFormatter que permite construir o padrão de formatação usando algum padrão já
    default de algum locale ou então algum padrão especifico.
     */
    public static void usingLocaleDateAndTime() {
        LocalDate date = LocalDate.of(2019, Month.APRIL, 1);
        Locale locale = new Locale("en", "GB");

        // Criamos uma formatação especifica (dia da semana, dia, nome do mês e ano)
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy", locale);

        // Aplica a formatação para o padrão específico ao locale de Inglês Britânico.
        String result = date.format(format);
        System.out.println(result);

        // Agora fazendo o caminho inverso (parsing)
        // Faz o parse do texto inputado pelo usuário para LocalDate considerando esse formato especfico.
        date = LocalDate.parse("Tuesday 31 Mar 2020", format);

        // Reinicializa o locale para Russo.
        locale = new Locale("ru");

        // Usamos o FormatStyle para dizer: qualquer que seja a formatação padrão média para esse locale específico.
        format = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale);
        result = date.format(format);
        System.out.println(result);
    }

    /*
    Localized resources.
    Nós temos que lidar não somente com datas, horas e números, mas também com qualquer outro texto que vamos exibir ou
    receber do usuário. Assim, todos esses diferentes tipos de textos, eles podem precisar ser traduzidos para diferentes
    idiomas e tratados de forma diferente dependendo do locale que o usuário prefere usar. Para isso, podemos produzir
    resource bundles. Eles podem ser produzidos como simples arquivos texto (.property files). Também podemos criá-los
    como classes. Mas no geral, é somente um arquivo de propriedades com pares chave-valor.

    messages.properties - O arquivo padrão pode ser em qualquer linguagem, a gente que decide. (Nesse caso, é japonês)
    hello=こんにちは {0}
    product={0}, 価格 {1}, 量 {2}, 賞味期限は {3}
    other=その他


    messages_en_GB.properties - Inglês Britânico.
    hello=Hello {0}
    product={0}, price {1}, quantity {2}, best before {3}


    messages_ru.properties - Russo.
    hello=Привет {0}
    product={0}, цена {1}, количество {2}, Употребить до {3}

    Eles podem ter essas posições de substituição
     */
    public static void localizedResources() {
        // Criamos o objeto de locale.
        Locale locale = new Locale("en", "GB");

        /* Digo para pegar meu bundle e usar a pasta, que é o nome do pacote, e o arquivo (sem o .properties)
        * e qual o locale eu quero usar. Isso recupera o bundle apropriado.  */
        ResourceBundle bundle = ResourceBundle.getBundle("resource.messages", locale);

        // Pega valores do bundle
        String helloPattern = bundle.getString("hello");

        // Quando a chave não existir no locale especifico, então ela sera recuperada do bundle default. Isso pode trazer
        // misturas estranhas de locales, então é bom cuidar.
        String otherMessage = bundle.getString("other");

        /* Substituindo as posições do bundle
        *
        * product={0}, price {1}, quantity {2}, best before {3}
        *
        * */

        // Valores iniciados para rodar executar o exemplo
        BigDecimal price = BigDecimal.valueOf(2.99);
        int quantity = 5;
        LocalDate date = LocalDate.of(2021, Month.APRIL, 10);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        DateTimeFormatter dateFomatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale);


        locale = new Locale("en", "GB");
//        bundle = ResourceBundle.getBundle("resources.messages", locale);
        bundle = ResourceBundle.getBundle("resource.messages", locale);

        // assume the following values are already formatted
        String name = "Cookie";
        String formattedPrice = currencyFormat.format(price);
        String formattedQuantity = numberFormat.format(quantity);
        String bestBefore = date.format(dateFomatter);

        // Recupera o padrão do bundle
        String pattern = bundle.getString("product");

        // Substitui os valores dentro do padrão especificado, na mesma ordem que aparecem.
        // Cuidar para esses valores também já estarem formatados no locale certo.
        String message = MessageFormat.format(pattern, name, price, quantity, bestBefore);
        System.out.println(message);
    }

    public static void main(String[] args) {
//        zonedDateTime();
//        specifyingLocale();
//        usingLocaleNumbers();
//        usingLocaleDateAndTime();
        localizedResources();

    }

}
