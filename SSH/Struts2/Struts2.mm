{"objectClass":"NSDictionary","root":{"objectClass":"MindNode","ID":"7E14Z","rootPoint":{"objectClass":"CGPoint","x":251.5,"y":2010.5},"lineColorHex":"#BBBBBB","children":{"0":{"objectClass":"MindNode","ID":"72WP7","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"Q66B4","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"31FDO","lineColorHex":"#DC306C","text":"通过ActionContext对象操作"},"objectClass":"NSArray"},"text":"完全解耦合的方式：只能访问域中的数据","markType":1},"1":{"objectClass":"MindNode","ID":"8P22S","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"O5R1I","lineColorHex":"#DC306C","text":"通过ServletActio\nStruts2\n\nStruts2的Servlet的API的访问\n\n完全解耦合的方式：只能访问域中的数据\n\n原生方式：既可以操作数据,也可以获取方法\nnContext对象操作"},"objectClass":"NSArray"},"text":"原生方式：既可以操作数据,也可以获取方法","markType":1},"2":{"objectClass":"MindNode","ID":"7NY84","lineColorHex":"#DC306C","text":"接口注入的方式(了解即可)"},"objectClass":"NSArray"},"text":"Struts2的Servlet的API的访问"},"1":{"objectClass":"MindNode","ID":"187CH","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"40A32","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"4W76L","lineColorHex":"#BF58F5","text":"全局结果页面"},"1":{"objectClass":"MindNode","ID":"9F1J8","lineColorHex":"#BF58F5","text":"局部结果页面"},"objectClass":"NSArray"},"text":"结果页面的分类"},"1":{"objectClass":"MindNode","ID":"T23MU","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"613X8","lineColorHex":"#BF58F5","text":"name属性"},"1":{"objectClass":"MindNode","ID":"6Y19W","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"O7Y95","lineColorHex":"#BF58F5","text":"dispatcher：默认值、\n                 转发","markType":1},"1":{"objectClass":"MindNode","ID":"BBNE4","lineColorHex":"#BF58F5","text":"redirect：重定向","markType":1},"2":{"objectClass":"MindNode","ID":"32M51","lineColorHex":"#BF58F5","text":"chin：转发到Action","markType":1},"3":{"objectClass":"MindNode","ID":"66DO2","lineColorHex":"#BF58F5","text":"redirectAction：重定向到\n                  Acrion","markType":1},"4":{"objectClass":"MindNode","ID":"Q6B66","lineColorHex":"#BF58F5","text":"stream：文件下载时使用"},"objectClass":"NSArray"},"text":"type属性"},"objectClass":"NSArray"},"text":"结果页面的配置"},"objectClass":"NSArray"},"text":"Struts2的结果页面配置"},"2":{"objectClass":"MindNode","ID":"86TRN","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"3M1W4","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"IEZH5","lineColorHex":"#26BBFF","text":"一种：提供set方法的方式"},"1":{"objectClass":"MindNode","ID":"7B5Q3","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"N92ST","lineColorHex":"#26BBFF","text":"页面提供OGNL表达式"},"1":{"objectClass":"MindNode","ID":"CC795","lineColorHex":"#26BBFF","text":"提供属性的get、set方法"},"objectClass":"NSArray"},"text":"二种：页面提供OGNL表达式的方式","markType":1},"objectClass":"NSArray"},"text":"属性驱动"},"1":{"objectClass":"MindNode","ID":"MWJ4H","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"9VO47","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"7P386","lineColorHex":"#26BBFF","text":"实现一个接口"},"1":{"objectClass":"MindNode","ID":"7R4U7","lineColorHex":"#26BBFF","text":"手动创建对象"},"objectClass":"NSArray"},"text":"三种：模型驱动的方式(**常用**)","markType":1},"objectClass":"NSArray"},"text":"模型驱动"},"2":{"objectClass":"MindNode","ID":"7442N","lineColorHex":"#26BBFF","text":""},"objectClass":"NSArray"},"text":"Struts2的数据封装"},"3":{"objectClass":"MindNode","ID":"578O4","lineColorHex":"#1BD6E7","children":{"0":{"objectClass":"MindNode","ID":"0UH38","lineColorHex":"#1BD6E7","text":"封装到list集合"},"1":{"objectClass":"MindNode","ID":"2K68R","lineColorHex":"#1BD6E7","text":"封装到map集合"},"objectClass":"NSArray"},"text":"复杂类型数据的封装(了解即可)"},"4":{"objectClass":"MindNode","ID":"JW9S3","lineColorHex":"#FFC700","children":{"0":{"objectClass":"MindNode","ID":"ODNT3","lineColorHex":"#FFC700","text":"OGNL：对象图导航语言,是一种功能强大的表达式语言。"},"1":{"objectClass":"MindNode","ID":"5864Y","lineColorHex":"#FFC700","children":{"0":{"objectClass":"MindNode","ID":"U68P6","lineColorHex":"#FFC700","text":"调用对象的普通方法"},"1":{"objectClass":"MindNode","ID":"H2KX2","lineColorHex":"#FFC700","text":"调用对象的静态方法"},"2":{"objectClass":"MindNode","ID":"OTCGW","lineColorHex":"#FFC700","text":"表达式串联"},"3":{"objectClass":"MindNode","ID":"OR935","lineColorHex":"#FFC700","text":"访问ActionContext的数据"},"4":{"objectClass":"MindNode","ID":"67S48","lineColorHex":"#FFC700","text":"操作集合"},"objectClass":"NSArray"},"text":"优点："},"2":{"objectClass":"MindNode","ID":"LN30G","lineColorHex":"#FFC700","children":{"0":{"objectClass":"MindNode","ID":"257Y8","lineColorHex":"#FFC700","text":"java环境"},"1":{"objectClass":"MindNode","ID":"3I4YK","lineColorHex":"#FFC700","text":"Struts2环境"},"objectClass":"NSArray"},"text":"OGNL入门 "},"objectClass":"NSArray"},"text":"OGNL"},"5":{"objectClass":"MindNode","ID":"28GV9","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"UM923","lineColorHex":"#DC306C","text":"什么是值栈：值栈是一个接口,实现了OgnlValueStack，值栈贯穿了Action的生命周期,相当于数据中转站(注：只创建一个了一个Action实例,就会创建一个值栈对象)"},"1":{"objectClass":"MindNode","ID":"Y31Y3","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"HSC26","lineColorHex":"#DC306C","text":"root：CompoundRoot,就是一个ArrayList。(所说的操作值栈，也就是操作root)","markType":1},"1":{"objectClass":"MindNode","ID":"HNTRQ","lineColorHex":"#DC306C","text":"context：OgnlContext,就是一个\nMap"},"objectClass":"NSArray"},"text":"值栈的内部结构"},"2":{"objectClass":"MindNode","ID":"5GGSS","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"B6U5G","lineColorHex":"#DC306C","text":"在ActionContext中获得值栈的引用"},"objectClass":"NSArray"},"text":"值栈和ActionContext关系"},"3":{"objectClass":"MindNode","ID":"5C94K","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"M55TK","lineColorHex":"#DC306C","text":"通过ActionContext对象获得"},"1":{"objectClass":"MindNode","ID":"S3444","lineColorHex":"#DC306C","text":"通过request对象获得"},"objectClass":"NSArray"},"text":"获得值栈"},"4":{"objectClass":"MindNode","ID":"66527","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"563FV","lineColorHex":"#DC306C","text":"利用Action本身在值栈中的特性,给Action定义属性和属性的get方法"},"1":{"objectClass":"MindNode","ID":"1J5U3","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"5VR7F","lineColorHex":"#DC306C","text":"push(Object obj)"},"1":{"objectClass":"MindNode","ID":"O195T","lineColorHex":"#DC306C","text":"set(String str,\n         Objext obj)"},"objectClass":"NSArray"},"text":"调用值栈本身的方法 "},"objectClass":"NSArray"},"text":"操作值栈"},"5":{"objectClass":"MindNode","ID":"ILG05","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"2JPV4","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"939F4","lineColorHex":"#DC306C","text":"获取对象数据"},"1":{"objectClass":"MindNode","ID":"H332Y","lineColorHex":"#DC306C","text":"获取集合数据"},"objectClass":"NSArray"},"text":"在页面中使用OGNL表达式获取"},"1":{"objectClass":"MindNode","ID":"1D881","lineColorHex":"#DC306C","text":"获取COntext数据"},"objectClass":"NSArray"},"text":"获取值栈的数据"},"6":{"objectClass":"MindNode","ID":"0HJ59","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"Z4IYH","lineColorHex":"#DC306C","text":"底层增强了request.getAttribute方法\n(如果作用域里找到了就用作用域的数据,作用域里找不到就去找值栈中的数据)"},"objectClass":"NSArray"},"text":"EL表达式为什么可以访问值栈数据"},"objectClass":"NSArray"},"text":"值栈ValueStack"},"6":{"objectClass":"MindNode","ID":"N64O9","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"OCJG2","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"WK3J7","lineColorHex":"#BF58F5","text":"获取COntext数据"},"1":{"objectClass":"MindNode","ID":"DA86S","lineColorHex":"#BF58F5","text":"构建map集合"},"objectClass":"NSArray"},"text":"#号"},"1":{"objectClass":"MindNode","ID":"YHEMM","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"Q4MV2","lineColorHex":"#BF58F5","text":"在配置文件中使用OGNL表达式"},"objectClass":"NSArray"},"text":"$号"},"2":{"objectClass":"MindNode","ID":"6TP12","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"24U1P","lineColorHex":"#BF58F5","text":"强制解析OGNL表达式"},"1":{"objectClass":"MindNode","ID":"8X73O","lineColorHex":"#BF58F5","text":"强制不解析OGNL表达式(基本不用)"},"objectClass":"NSArray"},"text":"%号"},"objectClass":"NSArray"},"text":"OGNL特殊字符"},"7":{"objectClass":"MindNode","ID":"1YDFU","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"VOD3C","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"U3AU7","lineColorHex":"#26BBFF","text":"拦截器：拦截对Struts2的Action的访问"},"1":{"objectClass":"MindNode","ID":"HGK5D","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"7KW42","lineColorHex":"#26BBFF","text":"过滤器过滤的是请求：HTML、Jsp、图片、servlet...."},"1":{"objectClass":"MindNode","ID":"UV306","lineColorHex":"#26BBFF","text":"拦截器拦截的是Action的访问,但是它可以拦截Action中的方法"},"objectClass":"NSArray"},"text":"与过滤器的区别："},"objectClass":"NSArray"},"text":"拦截器概述"},"1":{"objectClass":"MindNode","ID":"Z72MU","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"I834H","lineColorHex":"#26BBFF","text":"拦截器是AOP的思想(AOP思想：下部修改程序源代码的前提下,对程序进行扩展)"},"objectClass":"NSArray"},"text":"拦截器是Struts2的核心","markType":1},"2":{"objectClass":"MindNode","ID":"CBZ6V","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"61JU2","lineColorHex":"#26BBFF","text":"发送请求-->核心过滤器-->\nFilterDispatcher-->\nActionInvocation类的invoke方法。\n在这个方法内部递归执行拦截器(如果有下一个,就会执行下一个,没有,则到Action)根据Action的返回值Result跳转到相应的页面,\n还会倒序执行拦截器,最终响应到页面"},"objectClass":"NSArray"},"text":"Struts2的执行流程(面试)"},"3":{"objectClass":"MindNode","ID":"764QG","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"1V01H","lineColorHex":"#26BBFF","text":"1.编写一个类,实现接口或者继承类"},"1":{"objectClass":"MindNode","ID":"33EQ6","lineColorHex":"#26BBFF","children":{"0":{"objectClass":"MindNode","ID":"HO56E","lineColorHex":"#26BBFF","text":"定义、引入"},"1":{"objectClass":"MindNode","ID":"5136C","lineColorHex":"#26BBFF","text":"定义拦截器"},"objectClass":"NSArray"},"text":"2.配置拦截器"},"objectClass":"NSArray"},"text":"自定义拦截器"},"objectClass":"NSArray"},"text":"拦截器(**重点**)","markType":1},"8":{"objectClass":"MindNode","ID":"A1R82","lineColorHex":"#37C45A","children":{"0":{"objectClass":"MindNode","ID":"7S4RO","lineColorHex":"#37C45A","children":{"0":{"objectClass":"MindNode","ID":"65255","lineColorHex":"#37C45A","text":"判断标签\n(<s:if>、<s:elseif>、<s:else>)"},"1":{"objectClass":"MindNode","ID":"X4R43","lineColorHex":"#37C45A","text":"迭代标签\n(<iterator>)"},"2":{"objectClass":"MindNode","ID":"9SNUD","lineColorHex":"#37C45A","text":"其他标签"},"objectClass":"NSArray"},"text":"通用标签"},"1":{"objectClass":"MindNode","ID":"QKZJ2","lineColorHex":"#37C45A","text":"UI标签"},"objectClass":"NSArray"},"text":"标签库"},"objectClass":"NSArray"},"text":"Struts2"}}