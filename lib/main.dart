import 'package:flutter/material.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/Splash_Screen.dart';
import 'Routes/Routes.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Heal Fast',
      theme: ThemeData(
        scaffoldBackgroundColor: Colors.lightBlueAccent,
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      initialRoute: RoutesName.splashScreen,
      onGenerateRoute: Routes.generateRoute,
      home: const SplashScreen(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});


  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children:[
            Padding(
              padding: const EdgeInsets.only(bottom: 80),
              child: Container(
                margin: const EdgeInsets.all(11),
                width: 200,
                height: 200,
                decoration: const BoxDecoration(
                    color: Colors.green,
                    borderRadius: BorderRadius.all(Radius.circular(15)),
                    boxShadow: [
                      BoxShadow(
                        blurRadius: 6,
                      )
                    ]
                ),
                child: const Center(
                  child: Text(
                    'Welcome\nto\nHeal Fast',
                    textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold, color:Colors.red,),
                  ),
                ),
              ),
            ),

            SizedBox(
              width: 225,
              child: Padding(
                padding: const EdgeInsets.only(bottom: 20),
                child: ElevatedButton(onPressed: (){
                  Navigator.pushNamed(context,RoutesName.userRegister);
                },style: ElevatedButton.styleFrom(
                    elevation: 10,
                    shadowColor: Colors.black
                ),child: const Text('Register As User', style: TextStyle(fontSize: 18),),),
              ),
            ),

            SizedBox(
              width: 225,
              child: Padding(
                padding: const EdgeInsets.only(bottom: 20),
                child: ElevatedButton(onPressed: (){
                  Navigator.pushNamed(context, RoutesName.clinicRegister);
                },style: ElevatedButton.styleFrom(
                    elevation: 10,
                    shadowColor: Colors.black
                ), child: const Text('Register As Doctor', style: TextStyle(fontSize: 18),
                ),
                ),
              ),
            ),

            InkWell(
              onTap: (){
                Navigator.pushNamed(context,
                    RoutesName.login);
              },
              child: const Text("Allready A User",
                selectionColor: Colors.blue,
                style: TextStyle(
                    fontSize: 20,
                    decoration: TextDecoration.underline),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
