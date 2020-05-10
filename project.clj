(defproject net.b12n/deps "1.0.0"
  :description "Adding dependency at runtime using alembic"
  :url "https://github.com/agilecreativity/b12n-deps"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [alembic "0.3.2"]
                 ;; Note: for testing only
                 #_[hara/io.file "3.0.5"]]
  :repl-options {:init-ns b12n-deps.core})
