import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
    runApp(BMICalculatorApp());
}

class BMICalculatorApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            debugShowCheckedModeBanner: false,
        title: 'BMI Calculator',
        theme: ThemeData(
        primarySwatch: Colors.blue,
        ),
        home: BMICalculatorScreen(),
        );
    }
}

class BMICalculatorScreen extends StatefulWidget {
    @override
    _BMICalculatorScreenState createState() => _BMICalculatorScreenState();
}

class _BMICalculatorScreenState extends State<BMICalculatorScreen> {
    final TextEditingController _heightController = TextEditingController();
    final TextEditingController _weightController = TextEditingController();
    double? _bmi;
    String _resultText = '';

    void _calculateBMI() {
        final double? height = double.tryParse(_heightController.text);
        final double? weight = double.tryParse(_weightController.text);

        if (height == null || height <= 0 || weight == null || weight <= 0) {
            setState(() {
                _resultText = "Please enter non zero values for height and weight.";
                const ColorScheme.highContrastLight();
            });
            return;
        }

        setState(() {
            _bmi = weight / (height * height);

        });
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                    title: const Text('BMI Calculator',
                style: TextStyle(
                        color: Colors.blueAccent,
        ),),

        centerTitle: true,
        ),
        body: Padding(
        padding: const EdgeInsets.all(15.0),
        child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
        TextField(
            controller: _heightController,
            decoration: const InputDecoration(
                labelText: 'Height in meters',
            border: OutlineInputBorder(),
        ),
        keyboardType: TextInputType.number,
        ),
        const SizedBox(height: 15.0),
        TextField(
            controller: _weightController,
            decoration: const InputDecoration(
                labelText: 'Weight in kilograms',
            border: OutlineInputBorder(),
        ),
        keyboardType: TextInputType.number,
        ),
        const SizedBox(height: 22.0),
        ElevatedButton(
            onPressed: _calculateBMI,
            child: const Text('Calculate BMI',
                style: TextStyle(color: Colors.blueAccent)
        ,),
        ),
        const SizedBox(height: 22.0),
        if (_bmi != null)
            Text(
                'Your BMI: ${_bmi!.toStringAsFixed(2)}',
                style: const TextStyle(fontSize: 24.0,
                color: CupertinoColors.activeBlue),
        ),
        Text(
            _resultText,
            style: const TextStyle(fontSize: 20.0, color: Colors.red),
        ),
        ],
        ),
        ),
        );
    }
}