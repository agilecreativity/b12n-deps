# b12n-deps

[![Clojars Project](https://img.shields.io/clojars/v/net.b12n/deps.svg)](https://clojars.org/net.b12n/deps)
[![Dependencies Status](https://jarkeeper.com/agilecreativity/b12n-deps/status.svg)](https://jarkeeper.com/agilecreativity/b12n-deps)
![ClojarsDownloads](https://img.shields.io/clojars/dt/net.b12n/deps)

A Clojure library designed to add dependency to your project at runtime.
It uses [alembic](https://github.com/pallet/alembic) behind the scene.

## Usage

To add new dependency to your active REPL session at runtime try

For leiningen base project, you can add the following to your `project.clj`

```clojure
:profiles {:dev {:dependencies [[net.b12n/deps "1.0.0"]]}}
```

Then from your Clojure file you can then try something like

```clojure
(require '[b12n.deps.core :refer [add-depency]]')
(require '[alembic.still :refer [distill lein load-project]])

;; Add new dependency to your session without adding it to your project.clj or deps.edn
(add-dependency :hara/io.file "3.0.12")   ;;=> nil
(add-dependency "hara/io.file" "3.0.12")  ;;=> nil
(add-dependency '[hara/io.file "3.0.12"]) ;;=> nil

;; Now use it in the project
(require '[hara.io.file :as hf])

;; Test that we can actually call the function of this library
(hf/list ".")

;; Call lein task to printout the dependency tree
(alembic.still/lein deps :tree) ;;=> see your REPL

;; To make your change permanent, you can add the above dependency to your `project.clj` or `deps.edn` file as appropriate.
;; Then run the following command in your active session.
;; See: https://github.com/pallet/alembic/blob/develop/src/alembic/still.clj#L299

;; Tips: If you are using Emacs and install clj-refactor you can use this like
;; M-x cljr-add-project-dependency
;; Then you can use this dependency permanently in your project

(load-project "project.clj")    ;;=> see your REPL
(alembic.still/lein deps :tree) ;;=> see your REPL

;; Check if we can use the new dependency
(require '[hara.io.file :as hf])
(hf/list ".")

;;=> You should see the same result as before

;; useful for check out the source
(clojure.repl/source hf/list)
(clojure.repl/doc hf/list)
```

## TODO

- Find ways to add dependency vector to `project.clj` similar to [cljr-add-project-dependency](https://github.com/clojure-emacs/clj-refactor.el/wiki/cljr-add-project-dependency)

## License

Copyright © 2020 Burin Choomnuan

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
