(ns b12n.deps.core
  (:require [alembic.still :refer [distill
                                   lein
                                   load-project]]))

(defn add-dependency
  "Add project dependency at runtime via alembic.
  Example:
  (add-dependency :hara/io.file \"3.0.12\")
  (add-dependency \"hara/io.file\" \"3.0.12\")
  (add-dependency '[hara/io.file \"3.0.12\"])
  ;; Then require it normally
  (require '[hara.io.file :as hf])"
  ([dep-vector]
   (let [[lib-name lib-version] dep-vector]
     (add-dependency lib-name lib-version)))
  ([lib-name lib-version]
   (let [dep-name (symbol lib-name)
         dep-version (name lib-version)]
     (alembic.still/distill [dep-name dep-version]))))

(def add-project-dependency add-dependency)

(comment
  ;; Add new dependency to your session without adding it to your project.clj or deps.edn
  #_
  (require '[alembic.still :refer [distill lein load-project]])
  (require '[b12n.deps.core :refer [add-dependency]])

  (add-dependency :hara/io.file "3.0.12")   ;;=> nil
  (add-dependency "hara/io.file" "3.0.12")  ;;=> nil
  (add-dependency '[hara/io.file "3.0.12"]) ;;=> nil

  ;; Now use it in our project
  (require '[hara.io.file :as hf])

  ;; Test that we can actually call the function of this library
  (hf/list ".")

  ;; Call lein task to printout the dependency tree
  (alembic.still/lein deps :tree) ;;=> see your REPL

  ;; See: https://github.com/pallet/alembic/blob/develop/src/alembic/still.clj#L299
  ;; To make your change permanent, you may like to add this dependency to your `project.clj` or `deps.edn`
  ;; And then you can run the following function to confirm
  (load-project "project.clj")    ;;=> see your REPL
  (alembic.still/lein deps :tree) ;;=> see your REPL

  ;; Check if we can use the new dependency
  (require '[hara.io.file :as hf])
  (hf/list ".")

  )

(comment
  (require '[b12n.deps.core :refer [add-dependency]])
  (add-dependency '[hara/io.file "3.0.12"]) ;;=> nil

  (require '[hara.io.file :as hf])
  (alembic.still/lein deps :tree) ;;=> see your REPL
  (hf/list ".")

  ;; useful for check out the source
  (clojure.repl/source hf/list)
  (clojure.repl/doc hf/list)

  )
