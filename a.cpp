#include <jni.h>

int main(){
    JavaVM *jvm;                      // Pointer to the JVM (Java Virtual Machine)
    JNIEnv *env;                      // Pointer to native interface
    jint sigonAgent;
    jclass wrapper;
    jclass wrapperMain;

    jmethodID wrapperMethodPercept;
    jmethodID wrapperMethodAction;
    jmethodID wrapperMainMethod;


 //================== prepare loading of Java VM ============================
    JavaVMInitArgs vm_args;                        // Initialization arguments
    JavaVMOption* options = new JavaVMOption[1];   // JVM invocation options
    options[0].optionString = "-Djava.class.path=/home/rr/eclipse/java-2019-09/eclipse/sigon.jar";   // where to find java .class
    //options[0].optionString = "-Djava.class.path=/home/thiago/projetos/java_jar/Main.jar";   // where to find java .class
    vm_args.version = JNI_VERSION_1_6;             // minimum Java version
    vm_args.nOptions = 1;                          // number of options
    vm_args.options = options;
    vm_args.ignoreUnrecognized = false;     // invalid options make the JVM init fail

    //=============== load and initialize Java VM and JNI interface =============
    sigonAgent = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);  // YES !!
    delete options;    // we then no longer need the initialisation options.
    if (sigonAgent != JNI_OK) {
        // TO DO: error processing...
        std::cin.get();
        exit(EXIT_FAILURE);
    }
    //=============== Display JVM version =======================================
    std::cout << "JVM load succeeded: Version ";
    jint ver = env->GetVersion();
    std::cout << ((ver >> 16) & 0x0f) << "." << (ver & 0x0f) << endl;
    //wrapper = env->FindClass("Main");  // try to find the class
    wrapper = env->FindClass("perceptionExperiment/MainFinal");  // try to find the class

    if (wrapper == nullptr) {
        std::cerr << "ERROR: class not found !";
    }


    else {                                  // if class found, continue

        std::cout << "Class MyTest found" << endl;


        wrapperMainMethod = env->GetStaticMethodID(wrapper, "main", "([Ljava/lang/String;)V");  // find method

        if (wrapperMainMethod == nullptr)
            std::cerr << "ERROR: method void main() not found !" << endl;

        jstring jstrBuf = env->NewStringUTF("/home/rr/awareness.on");

        env->CallStaticObjectMethod(wrapper, wrapperMainMethod,jstrBuf);



        //        wrapperMethodPercept = env->GetStaticMethodID(wrapper, "percept", "(Ljava/lang/String;)Ljava/lang/String;");  // find method
                  wrapperMethodPercept = env->GetStaticMethodID(wrapper, "percept", "(Ljava/lang/String;)V");  // find method
                  wrapperMethodAction = env->GetStaticMethodID(wrapper, "getAction", "()Ljava/lang/String;");  // find method

        if (wrapperMethodPercept == nullptr)
            std::cerr << "ERROR: method void wrapperMethodPercept() not found !" << endl;

        if (wrapperMethodAction == nullptr)
            std::cerr << "ERROR: method void wrapperMethodAction() not found !" << endl;
    }


 jstring jstrBuf = env->NewStringUTF("car(x, yes);yes;yes");
    env->CallStaticObjectMethod(wrapper, wrapperMethodPercept, jstrBuf);


    jstring result =  (jstring) env->CallStaticObjectMethod(wrapper, wrapperMethodAction);
    const char* out_str = env->GetStringUTFChars(result, NULL);
    if ( out_str == nullptr)
        std::cerr << "NoReturn" << endl;






    return 0;

}