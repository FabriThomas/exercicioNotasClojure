(ns exNota.core)

(defn calcConceito [nota]
  (cond
    (>= nota 90) "A"
    (>= nota 80) "B"
    (>= nota 70) "C"
    (>= nota 60) "D"
    :else        "F"))

(defn calcDesempenho [media]
  (if (>= media 80)
    (println "Turma excelent!")
    (if (>= media 60)
      (println "Bom desempenho!")
      (println "É necessário melhorar!"))))

(defn -main []
  (print "Quantos alunos na turma? ") (flush)
  (let [qtdAlunos (Integer/parseInt (read-line))]
    (let [somaNotas (atom 0)
          aprovados (atom 0)]
      (dotimes [n qtdAlunos]
        (println "\n--- Aluno" (inc n) "---")
        (print "Nome do aluno: ") (flush)
        (let [nome (read-line)
              _    (do (print "Nota do aluno: ") (flush))
              nota (Integer/parseInt (read-line))
              conceito (calcConceito nota)]
          (println "Aluno:" nome "/ Nota:" nota "/ Conceito:" conceito)
          (swap! somaNotas + nota)
          (if (not= conceito "F")
            (swap! aprovados inc))))
      (println "\n\n--- ESTATÍSTICAS FINAIS ---")
      (if (pos? qtdAlunos)
        (let [somaFinal @somaNotas
              media (/ (double somaFinal) qtdAlunos)]
          (println (str "Média da Turma: " (format "%.2f" media)))
          (println "Alunos aprovados:" @aprovados)
          (calcDesempenho media))

        (println "Nenhum aluno foi inserido.")))))
