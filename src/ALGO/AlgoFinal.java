package ALGO;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.*;




import Chess.LogInfo;
import Chess.PredictionEvaluation;

import mypackage.Children2;
import mypackage.ClassField2;
import mypackage.ClassTrace2;
import mypackage.ColumnGroup;
import mypackage.Method;
import mypackage.MethodTrace;
import mypackage.Requirement;


public class AlgoFinal extends JFrame {
	public static boolean InheritanceFlag=false; 
	public static boolean InterfaceImplementationFlag=false; 
	public static boolean InheritanceRecursion=false; 

	
	public static boolean RecursiveCallFlag=false; 

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException
	 *             If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	ResultSet rs = null;
	// Connect to MySQL

	PredictionEvaluation TotalPattern = new PredictionEvaluation();
	PredictionEvaluation RemainingPattern = new PredictionEvaluation();
	PredictionEvaluation NonOwnerClassPattern = new PredictionEvaluation();

	
	
	
	
	static List<MethodTrace> methodtraces = new ArrayList<MethodTrace>();
	HashMap<String, List<String>> classMethodsHashMap = new HashMap<String, List<String>>();
	public static HashMap<String, MethodTrace> methodtraces2HashMap = new HashMap<String, MethodTrace>();
	LinkedHashMap<String, ClassTrace2> methodtracesRequirementClass = new LinkedHashMap<String, ClassTrace2>();


	
	
	
	
	
	
	JTable table = new JTable();
	// File fout = new
	// File("C:\\Users\\mouna\\new_workspace\\SpoonProcessorFinal\\TableLog.txt");

	public final String userName = "root";
	public final String password = "123456";
	List<Method> CallerMethodListFinal = new ArrayList<Method>();
	List<Method> CalleeMethodListFinal = new ArrayList<Method>();

	public List<Method> getCallerMethodListFinal() {
		return CallerMethodListFinal;
	}

	public void setCallerMethodListFinal(List<Method> callerMethodListFinal) {
		CallerMethodListFinal = callerMethodListFinal;
	}

	public List<Method> getCalleeMethodListFinal() {
		return CalleeMethodListFinal;
	}

	public void setCalleeMethodListFinal(List<Method> calleeMethodListFinal) {
		CalleeMethodListFinal = calleeMethodListFinal;
	}

	public AlgoFinal(String ProgramName) throws Exception {

	
//		List<MethodTrace> methodtracesNew = InitializePredictionsHashMap2(methodtraces2);
		TracePredictionFunction( ProgramName);

	}

	

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	/**
	 * @throws Exception **********************************************************************************************************************************************/

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void TracePredictionFunction( String ProgramName) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bwfile1 =null; 
		BufferedWriter bwfile2 = null ; 
		BufferedWriter bwfile3 = null ; 
		
	if(ProgramName.equals("chess")) {
		File file1log = new File("C:\\Users\\mouna\\ownCloud\\Share\\dumps\\LatestLogFiles\\InterfacesNewRule.txt");
		FileOutputStream fosfila1 = new FileOutputStream(file1log);
		 bwfile1 = new BufferedWriter(new OutputStreamWriter(fosfila1));
		
		File file2log = new File("C:\\Users\\mouna\\ownCloud\\Share\\dumps\\LatestLogFiles\\LogFileUnderstandFP.txt");
		FileOutputStream fosfila2 = new FileOutputStream(file2log);
		 bwfile2 = new BufferedWriter(new OutputStreamWriter(fosfila2));
		 
			File file3log = new File("C:\\Users\\mouna\\ownCloud\\Share\\dumps\\LatestLogFiles\\LogFileUnderstandFN.txt");
			FileOutputStream fosfila3 = new FileOutputStream(file3log);
			 bwfile3 = new BufferedWriter(new OutputStreamWriter(fosfila3));
	}
		// TODO Auto-generated method stub
		LogInfo.CreateLogFiles(ProgramName);

		
		

	
		
		DatabaseReading DatabaseReading= new DatabaseReading(ProgramName); 
		
		methodtraces2HashMap = DatabaseReading.getMethodtracehashmap();
		methodtraces = DatabaseReading.getMethodtracesList(); 

		
		

		LinkedHashMap<String, LogInfo> LogInfoHashMap = new LinkedHashMap<String, LogInfo>();
		

		Collection<MethodTrace> MethodTracesHashmapValues = methodtraces2HashMap.values();


		
	
		

	
		for(MethodTrace meth: MethodTracesHashmapValues) {
			System.out.println(meth);
			MethodList extendedcallers = meth.Method.getExtendedCallers(new Requirement("1","1")); 
			System.out.println("CALLERS =====>"+extendedcallers);
			MethodList extendedcallees = meth.Method.getExtendedCallees(new Requirement("1","1")); 
			System.out.println("CALLEES =====>"+extendedcallees);
			System.out.println("**********************");
		}
		System.out.println("END");
		
	}
	

	
	

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	public void SetLogFileIterations(Collection<MethodTrace> methodTracesHashmapValues, List<MethodTrace> methodtraces22, LinkedHashMap<String, LogInfo> LogInfoHashMap, int ITERATION ) {
		// TODO Auto-generated method stub
		for (MethodTrace methodtrace : methodTracesHashmapValues) {
			String ReqMethod = methodtrace.Requirement.ID + "-" + methodtrace.Method.ID;
			LogInfo LogInfo = LogInfoHashMap.get(ReqMethod);
			List<String> myits = LogInfo.getIterationValues();
			

			if (myits.size() == ITERATION + 1) {
				//System.out.println(myits.get(ITERATION));

			} else {
				myits.add(",");
				LogInfo.setIterationValues(myits);
			}

			for (String it : myits) {
				//System.out.println("it" + it + " it");
			}
			LogInfoHashMap.put(ReqMethod, LogInfo);
		}
	}

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	public PredictionValues SubstractPredictionValues(PredictionValues totalPredictionValues,
			PredictionValues ownerClassPredictionValues) {
			
		int totalT = totalPredictionValues.T; 
		int totalN=totalPredictionValues.N; 
		int totalE=totalPredictionValues.E;
		
		int ownerT=ownerClassPredictionValues.T; 
		int ownerN=ownerClassPredictionValues.N; 
		int ownerE=ownerClassPredictionValues.E; 

		
		int remainingT= totalT-ownerT; 
		int remainingN= totalN-ownerN;
		int remainingE= totalE;
		PredictionValues RemainingPredictionValues= new PredictionValues(); 
		RemainingPredictionValues.setT(remainingT);
		RemainingPredictionValues.setN(remainingN);
		RemainingPredictionValues.setE(totalE);
				return RemainingPredictionValues;
		// TODO Auto-generated method stub
		
	}

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	public void ResetAllTraceSetFlags(HashMap<String, MethodTrace> methodtraces2HashMap2) {
		// TODO Auto-generated method stub
		
		for(String key: methodtraces2HashMap2.keySet()) {
			MethodTrace MethodTrace = methodtraces2HashMap2.get(key); 
			MethodTrace.setTraceSet(false);
		}
		
	}

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	
	/************************************************************************************************************************************************/
	/**
	 * @param methodtraces2HashMap2 **********************************************************************************************************************************************/
	public void CountTracesClassesValues(PredictionValues PredictionClassTraceBefore,  HashMap<String, MethodTrace> methodtraces2HashMap2) {
		// TODO Auto-generated method stub
		HashMap<String, String> myhashmap= new HashMap<String, String> (); 
		
		for(String mykey: methodtraces2HashMap2.keySet()) {
			String reqClass= methodtraces2HashMap2.get(mykey).Requirement.ID+"-"+methodtraces2HashMap2.get(mykey).Method.Owner.ID; 
//			myhashmap.put(reqClass, methodtraces2HashMap2.get(mykey).Method.Owner.DeveloperGold);
			myhashmap.put(reqClass, DatabaseInput.OwnerTraceHashMap.get(reqClass));
		
		}
		
		
		
		
			for(String mykey: myhashmap.keySet()) {
				 String value = myhashmap.get(mykey); 
				
				PredictionClassTraceBefore.ComputePredictionValues(PredictionClassTraceBefore,value);
			}
		
		
		
		
	}
	
	
	

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	private LinkedHashMap<String, LogInfo> InitializeHashMapWithPrecisionRecall(
			Collection<MethodTrace> methodTracesHashmapValues, LinkedHashMap<String, LogInfo> logHashMapRemaining) {
		// TODO Auto-generated method stub
		
		for (MethodTrace methodtrace : methodTracesHashmapValues) {
			
			
			methodtrace.setPrediction("E");
			
		}
	
		return logHashMapRemaining;
	}

	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	/**
	 * @param methodtracesRequirementClass2 
	 * @return
	 * @throws Exception 
	 **********************************************************************************************************************************************/

	private void GenerateNewValuesInTracesClasses(
			LinkedHashMap<String, ClassTrace2> methodtracesRequirementClass2) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<MethodTrace> MethodTracesHashmapValues = methodtraces2HashMap.values();

		for (MethodTrace methodtrace : MethodTracesHashmapValues) {


			// PATTERN 0
			
			MethodList children = methodtrace.Method.Children; 
			MethodList parents = methodtrace.Method.Superclasses; 
			MethodList implementations = methodtrace.Method.Implementations; 
			MethodList interfaces = methodtrace.Method.Interfaces; 
			ClassTraceList ChilrenGold= new ClassTraceList(); 
			ClassTraceList ParentsGold= new ClassTraceList(); 
			ClassTraceList ImplementationsGold= new ClassTraceList(); 
			ClassTraceList InterfacesGold= new ClassTraceList(); 
			for(Method child:children) {
				String ChildGold = child.Owner.DeveloperGold; 
				ChilrenGold.add(ChildGold); 
			}
			
			
			for(Method parent:parents) {
				String ParentGold = parent.Owner.DeveloperGold; 
				ParentsGold.add(ParentGold); 
			}
			for(Method myinterface:interfaces) {
				String InterfaceGold = myinterface.Owner.DeveloperGold; 
				InterfacesGold.add(InterfaceGold); 

			}
			for(Method implementation:implementations) {
				String ImplementationGold = implementation.Owner.DeveloperGold; 
				ImplementationsGold.add(ImplementationGold); 
			}
			
			
			
			if(methodtrace.Method.Owner.DeveloperGold.equals("E") ) {
				if(ImplementationsGold.AllTs()) {
					methodtrace.Method.Owner.DeveloperGold="T";
				}else if(InterfacesGold.AllTs()) {
					methodtrace.Method.Owner.DeveloperGold="T";

				}else if(ParentsGold.AllTs()) {
					methodtrace.Method.Owner.DeveloperGold="T";

				}else if(ChilrenGold.AllTs()) {
					methodtrace.Method.Owner.DeveloperGold="T";

				}
				
				
				 if(ImplementationsGold.AllNs()) {
					methodtrace.Method.Owner.DeveloperGold="N";
				}else if(InterfacesGold.AllNs()) {
					methodtrace.Method.Owner.DeveloperGold="N";

				}else if(ParentsGold.AllNs()) {
					methodtrace.Method.Owner.DeveloperGold="N";

				}else if(ChilrenGold.AllNs()) {
					methodtrace.Method.Owner.DeveloperGold="N";

				}
			}
			
		}
	}
	
	
	
	
	
	
	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	/**
	 * @param logInfoHashMap 
	 * @param programName **********************************************************************************************************************************************/
	public void SetSubjectGoldDeveloperGoldEqualityFlag(HashMap<String, MethodTrace> methodTraceHashMap,
			PredictionEvaluation nEWPATTERNMethodFields2, LinkedHashMap<String, LogInfo> logInfoHashMap, String programName) {
		for (String mykey : methodTraceHashMap.keySet()) {
			
			 LogInfo loginfo = logInfoHashMap.get(mykey);
			MethodTrace methodtraceValue = methodTraceHashMap.get(mykey); 
			if( programName.equals("gantt")||programName.equals("jhotdraw") ) {
				String reqClass= methodtraceValue.Requirement.ID+"-"+methodtraceValue.Method.Owner.ID; 

//				if (methodtraceValue.Method.Owner.DeveloperGold.equals(methodtraceValue.Method.Owner.SubjectGold) ) 
					if (DatabaseInput.OwnerTraceHashMap.get(reqClass).equals(DatabaseInput.SubjectTraceHashMap.get(reqClass)) )

				{
					loginfo.setSubjectDeveloperEqualityFlag(true);
					logInfoHashMap.put(mykey, loginfo); 
					
					
					System.out.println(mykey);
					MethodTrace methodtrace = methodTraceHashMap.get(mykey); 
					methodtrace.setSubjectDeveloperEqualityFlag(true);
					methodTraceHashMap.put(mykey, methodtrace); 
				}
			}
			

		}
		
	}
	
	
	
	
	
	

	
	
	

	


	/************************************************************************************************************************************************/
	/************************************************************************************************************************************************/
	/**
	 * @throws Exception **********************************************************************************************************************************************/
	public static void main(String[] args) throws Exception {
		
		
//		String ProgramName = "chess";
//		AlgoFinal frame = new AlgoFinal(
//				ProgramName);
//
		String ProgramName2 = "dummdatabase";
		AlgoFinal	 frame = new AlgoFinal(ProgramName2);


//		String ProgramName3 = "itrust";
//			 frame = new AlgoFinal(ProgramName3);
//
//		
//		String ProgramName4 = "jhotdraw";
//				frame = new AlgoFinal(ProgramName4);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}



}