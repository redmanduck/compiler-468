import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Hashtable;
public class TinyGenerator {
	IRCollection IR;
	HashMap<Instruction, Instruction[]> map_ISA;
	Hashtable<String, Register> reg_map_ir_tiny;
	LinkedHashSet<Id> usedSymbols; //contains all LVALUES
	
	public TinyGenerator(IRCollection _irb) {
		IR = _irb;
		//generate IR -> asm map
		map_ISA = new HashMap<Instruction, Instruction[]>(); 
		reg_map_ir_tiny = new Hashtable<String, Register>();
		usedSymbols = new LinkedHashSet<Id>(); //these are bunch of used stuff 
		loadIRMapping();
	}
	
	private void loadIRMapping(){
		map_ISA.put(ISA.ADDI, new Instruction []{ISA.addi});
		map_ISA.put(ISA.ADDF, new Instruction []{ISA.addr});
		map_ISA.put(ISA.SUBI, new Instruction []{ISA.subi});
		map_ISA.put(ISA.SUBF, new Instruction []{ISA.subr});
		map_ISA.put(ISA.WRITEI, new Instruction []{ISA.sys_writei});
		map_ISA.put(ISA.WRITEF, new Instruction []{ISA.sys_writer});
		map_ISA.put(ISA.WRITES, new Instruction []{ISA.sys_writes});

		map_ISA.put(ISA.READI, new Instruction []{ISA.sys_readi});
		map_ISA.put(ISA.READF, new Instruction []{ISA.sys_readr});

		map_ISA.put(ISA.STOREI, new Instruction []{ISA.move});
		map_ISA.put(ISA.STOREF, new Instruction []{ISA.move});
		map_ISA.put(ISA.MULTI, new Instruction []{ISA.muli});
		map_ISA.put(ISA.MULTF, new Instruction []{ISA.mulr});
		map_ISA.put(ISA.DIVI, new Instruction []{ISA.divi});
		map_ISA.put(ISA.DIVF, new Instruction []{ISA.divr});
		
		map_ISA.put(ISA.GEI, new Instruction [] {ISA.cmpi, ISA.jge});
		map_ISA.put(ISA.GEF, new Instruction [] {ISA.cmpr, ISA.jge});
		map_ISA.put(ISA.LEI, new Instruction [] {ISA.cmpi, ISA.jle});
		map_ISA.put(ISA.LEF, new Instruction [] {ISA.cmpr, ISA.jle});

		map_ISA.put(ISA.LTI, new Instruction [] {ISA.cmpi, ISA.jlt}); //what about LEI?
		map_ISA.put(ISA.GTI, new Instruction [] {ISA.cmpi, ISA.jgt});

		map_ISA.put(ISA.EQI, new Instruction [] {ISA.cmpi, ISA.jeq});
		
		map_ISA.put(ISA.NEI, new Instruction [] {ISA.cmpi, ISA.jne});
		
		map_ISA.put(ISA.JUMP, new Instruction [] {ISA.jmp}); //uncodntional jump
		map_ISA.put(ISA.RET, new Instruction[]{ISA.__skip});
		map_ISA.put(ISA.LINK, new Instruction[]{ISA.__skip});
		map_ISA.put(ISA.LABEL, new Instruction []{ISA.label});
	}

	public String translate() {
		StringBuffer code = new StringBuffer();
		
		
		for(IRNode n : IR){
			String S = generate_asm(n);
			if(S == null) continue;
			code.append(S + "\n");
		}
		int vardec_offsets = 0;
		for(Id symbol: usedSymbols){
			String s =  String.format("var %s\n", symbol.getReferenceName());
			if(symbol.getType() == "STRING"){
				s = String.format("str %s %s\n", symbol.getReferenceName(), symbol.getStrValue());
			}
			code.insert(vardec_offsets, s);
			vardec_offsets += s.length();
		}
		code.append(ISA.sys_halt.getName() + "\n");
		code.insert(0, ";tiny code\n");
		
		return (code.toString());
	}

	private String getField(String st, int i){
		String [] s = st.split(" ");
		return s[i];
	}

	private String generate_asm(IRNode irn){	
		Instruction irx = irn.getInstruction();
		Instruction [] possible_instructions = map_ISA.get(irx);
		
		String ircode = irn.toString();
		
		if(possible_instructions == null){
			System.err.println(";No Instruction Mapping is defined for " + irx.getName());
			return null;
		}		
		
		Instruction tiny = possible_instructions[0]; //first 
		if(tiny == ISA.__skip) return null;
		
		if(irn.getFormat() == IRNode.FORMAT_D){
			
			usedSymbols.add(irn.getIdOperand(4));
			return tiny.getName() + " " + irn.getIdOperand(4).getReferenceName();
		}else if(irn.getFormat() == IRNode.FORMAT_DD){
			
			//Note we cannot do move id1, id2 , we have to do
			//move id1, r1
			//move r1, id2

			usedSymbols.add(irn.getIdOperand(3));
			Register dest = TempRegisterFactory.createTiny();

			String asms = ISA.move.getName() + " " + irn.getIdOperand(1).getReferenceName() +  " " + dest.toTiny() + "\n";
			asms += tiny.getName() + " " + dest.toTiny() + " " + irn.getIdOperand(3).getReferenceName();
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_IR || irn.getFormat() == IRNode.FORMAT_FR){
			
			Register dest = TempRegisterFactory.createTiny();
			reg_map_ir_tiny.put(getField(ircode,2), dest);
			
			return tiny.getName() + " " + getField(ircode, 1) + " " + dest.toTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_RD){
			usedSymbols.add(irn.getIdOperand(3));

			return tiny.getName() + " " + TempRegisterFactory.previous().toTiny() + " " + irn.getIdOperand(3).getReferenceName();
			
		}else if(irn.getFormat() == IRNode.FORMAT_DDR){

			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			Id d2 = irn.getIdOperand(2);
			Id d1 = irn.getIdOperand(1);
			
			usedSymbols.add(d2);
			usedSymbols.add(d1);
			
			String asms = move_op + " " + d1.getReferenceName() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  d2.getReferenceName() + " " + reg.toTiny();
			
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_RRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			String asms = move_op + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  reg_map_ir_tiny.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_RDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			Id d = irn.getIdOperand(2);
			usedSymbols.add(d);
			String asms = move_op + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + d.getReferenceName()  + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_DRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			usedSymbols.add(irn.getIdOperand(1));
			String asms = move_op + " " + irn.getIdOperand(1).getReferenceName() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + reg_map_ir_tiny.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_O){
			
			return tiny.getName();
			
		}else if(irn.getFormat() == IRNode.FORMAT_S){
			
			return tiny.getName() + " " + getField(ircode, 1);
		}else if(irn.getFormat() == IRNode.FORMAT_DRT){
			
			String asms =  possible_instructions[0].getName() + " " + getField(ircode,1) + " " + reg_map_ir_tiny.get(getField(ircode,2)).toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);
			return asms;	
		}else if(irn.getFormat() == IRNode.FORMAT_DDT){
			
			Register dest = TempRegisterFactory.createTiny();
			
			String asms = ISA.move.getName() + " " + getField(ircode,2) + " " + dest.toTiny()  + "\n";
			
		    asms += possible_instructions[0].getName() + " " +  getField(ircode,1) + " " + dest.toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_T){
			return tiny.getName() +  " " + getField(ircode, 1);
		}else{
			return "<unknown format>" + irn.getFormat();
		}
	}
	
	
	
}
