package com.lzh.interview.jdk;

/**
 * Created by lizhuohang on 2016/12/21.
 *
 * 将方法声明为final那有两个原因，第一就是说明你已经知道这个方法提供的功能已经满足你要求，不需要进行扩展，
 * 并且也不允许任何从此类继承的类来覆写这个方法,但是继承仍然可以继承这个方法，也就是说可以直接使用。
 *
 * 第二就是允许编译器将所有对此方法的调用转化为inline调用的机制，它会使你在调用final方法时，直接将方法主体插入到调用处，
 * 而不是进行例行的方法调用，例如保存断点，压栈等，这样可能会使你的程序效率有所提高，
 * 然而当你的方法主体非常庞大时，或你在多处调用此方法，那么你的调用主体代码便会迅速膨胀，可能反而会影响效率，所以你要慎用final进行方法定义。
 *
 */
public class ExplainOfFinal {
    //final class can not be extended by any class

    //final variables can not be modified

    //1.final method can not be overwrote in any extending class but can be used by the extending class
    //2.final method allows the compiler to translate all calls to this method into a inline call mechanism , witch means that
    //  the compiler can insert the function body into the called place.
    //  This may improve the efficiency of the program at sometimes, but if the method body is very large and called at many places,
    //  the final method may have a negative impact on Performance.
}
