class Parser {
  constructor(tokens) {
    this.tokens = tokens;
    this.tokenIndex = 0;
  }

  get currentToken(){
    return this.tokens[this.tokenIndex];
  }

  eat(token){
    if( this.currentToken === token ){
      console.log(this.currentToken);
      this.tokenIndex++;
    } else {
      throw "mismatched token: " + token + " =/= " + this.currentToken;
    }
  }

  start(){
    try {
      this.handleA();

      if(this.tokenIndex < this.tokens.length){
        throw "leftover tokens" ;
      }
    } catch (e) {
      console.log("error: " + e);
    }
  }

  handleA(){
    if( this.currentToken === "a" ){
      this.eat("a");
      this.handleB();
    } else {
      this.eat("c");
    }

    console.log("A");
  }

  handleB(){
    this.eat("b");

    console.log("B");
  }
}

console.log("parse: ab");
(new Parser(["a", "b"])).start();

console.log("parse: cb");
(new Parser(["c", "b"])).start();

console.log("parse: aa");
(new Parser(["a", "a"])).start();
