package com.nerddash.aulago.db;

//import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;
import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.Operations;

public class OperacoesComunsBD {
	
	public static final Operation LIMPA_TUDO = Operations.deleteAllFrom("ALUNOS", "PROFESSORES", "BUSCAS", "OFERTAS", "CONTRATOS");

	public static final Operation RESET_AUTOINCREMENT_H2 = sql(
			"ALTER TABLE ALUNOS ALTER COLUMN id RESTART WITH 1",
			"ALTER TABLE PROFESSORES ALTER COLUMN id RESTART WITH 1",
			"ALTER TABLE BUSCAS ALTER COLUMN id RESTART WITH 1",
			"ALTER TABLE OFERTAS ALTER COLUMN id RESTART WITH 1",
			"ALTER TABLE CONTRATOS ALTER COLUMN id RESTART WITH 1");

	private OperacoesComunsBD() {
	}

}
