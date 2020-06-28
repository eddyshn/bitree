# 1. export
```javascript
//a.js
export const name = "eddy";

export function hello(name) {
    console.log("hello", name);
}

//b.js
import { name, hello} from "./a.js";

hello(name);//hello eddy

```

# 2. export default
```javascript
//a-default.js
const name = "eddy default";

function hello(name) {
    console.log("hello", name);
}

export default {name, hello}

//b-default.js
import xx from './a-default.js';
xx.hello(xx.name);//hello eddy default

```
```haml
<script type="module" src="b.js"></script>
<script type="module" src="b-default.js"></script>
```

