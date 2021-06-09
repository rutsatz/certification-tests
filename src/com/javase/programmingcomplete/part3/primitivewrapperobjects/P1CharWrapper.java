package com.javase.programmingcomplete.part3.primitivewrapperobjects;


public class P1CharWrapper {
    public static void main(String[] args) {
        testStringIntern();
    }

    /*

    Strings são imutáveis e qualquer operação produz uma nova string.

    Você pode criar uma nova instância de String usando o operador new. No entanto, não é recomendado
    fazer isso, pois a string tem o seu próprio jeito de iniciar usando as aspas duplas "". Isso tem relação com
    a maneira que as string são alocadas em memória no Java. A JVM faz algumas otimizações com operações com String.
    Ela mantém uma única cópia de cada literal de String (esse processo é chamado de interning). Existe uma área especial
    para menter essas cópias que serão reusadas, chamadas de String Pool.

    Qualquer tentativa de alterar alguma String vai criar uma nova String. Por isso as String literals podem
    ser compartilhadas usando o pool de strings. Operações como trim(), toLowerCase(), substring(), etc,
    nenhuma delas modifica a String original, mas sim cria uma nova cópia no pool de string. Se já existir
    essa referencia no pool, a modificação retorna a referencia para essa string que já está no pool.

    Se alguma string no pool de string não for mais referencia, como por exemplo, quando voce faz algo como
    a = a.trim(), a variavel original continua no pool, até que seja coletada pelo GC.

    Usar o método .concat() é o mesmo que usar o sinal de + com duas strings.
 */
    public static void testStringIntern() {
        String b = "Hello"; // Cria uma váriavel normal.
        String c = b.intern(); // Como ainda não existe no pool de strings, adiciona no pool e retorna essa referencia.
        String d = c.intern(); // Recupera a referencia do pool de strings. Aponta para o mesmo objeto que c aponta.

        System.out.println("b " + b.hashCode());
        System.out.println("c " + c.intern());
        System.out.println("d " + d);

        String a = "Hello"; // Referencia a string hello no pool de strings

        // Coloca a string World no pool de string. Cria uma nova String concatenada
        // no pool de string em que a variavel e vai apontar.
        String e = a.concat("World");
    }

    /*

     */
    public static void testStringWithArimethics() {
        /*
        A avaliação das operações é feito da esquerda para a direita. Então nesse caso em que a operação
        aritmética é feita primeiro, é realizada a soma entre os dois número e depois feito a concatenação com a String.
        Se quiser mudar a precedência, use parenteses.
         */
        String s = "";
        s = 1 + 1 + "u"; // s is 2u
        s = "u" + 1 + 1; // s is u11
        s = "u" + (1 + 1); // s is u2

    }

    // # String index #
    /*
    O indice usado internamente começa em 0. Se usar o a.indexOf('o') e o 'o' não existir, o indice retornado será -1.
    Se tentar usar o a.charAt(10) e a posição não existir, irá lançar um StringIndexOutOfBoundsException.
     */


    // # StringBuilder #
    // Introduction
    /*
    StringBuilder é mutável.

    A classe String pode não ser muito eficiente para lidar com grandes quantidades de texto com várias manipulações,
    como adicionar, remover caracteres, mudar para minusculo, maiusculo, etc. Para manipulações complexas de texto, é
    recomendado usar o StringBuilder.

    Operações em cima do StringBuilder modificam a String daquela instância do StringBuilder.

    Ela tem algumas operações igual a classe String possui, como o substring, charAt, indexOf, que funcionam de forma
    parecida. Mas também temos métodos que modificam o seu conteúdo original, como append, insert, delete, reverse.
    Você pode olhar o tamanho atual da String com a.length(). Mas também pode olhar a capacidade total do StringBuilder
    antes de ele precisar fazer um rescaling de memória (a.capacity()).

    Se você tentar fazer um insert de uma posição que não existe (a.insert(4, 's')), você vai receber um IndexOutOfBoundsException.
    Vocẽ não pode pular posição. Se quisesse um espaço em branco na posição 3, você deveria fazer um insert dessa posição primeiro.

     */
    public static void testStringBuilder() {
        /*
        Por padrão, cria um String com capacidade de 16 caracteres. Ele vai ir incrementando o tamanho se precisar
        de mais espaço.
         */
        new StringBuilder();
        /*
        Você pode inicializar o StringBuilder com uma capacidade inicial específica. Também irá crescer a capacidade se
        for necessário. Se você sabe o tamanho que vai precisar, é bom passar o tamanho para evitar memory scalling.
        Mas se não souber, não tem problema pois ele irá crescer conforme for necessário. Ou seja, você não terá nenhum
        erro ou exception relacionado a tamanho, mas talvez tenha problemas relacionados a performance.
         */
        new StringBuilder(100);

        /*
        Vocẽ também pode inicializar com algum texto pré-definido.
         */
        new StringBuilder("text");

    }
}
