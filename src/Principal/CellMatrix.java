
package Principal;

public class CellMatrix {
    
    private int[][][] cooMatrix = new int[8][8][2];   //Tiene todas las coordenadas.
    private int[][] pieceMatrix = new int[8][8];      //0 peon, 1 torre, 2 caballo, 3 alfil, 4 reina, 5 rey, 6 maharajá, 7 empty.
    public int[] currentPositionMatrix = new int[2], posToMove= new int[2], PosCurrentPieceBefore= new int[2]; //Muestra en qué celda está el usuario.
    public boolean gameStarted  = false, temp=false, maharajaReady=false, maharajaInBoard=true, awaitingMovement=false, gameOver=false;

    
    // Start: Constructor
    public void CellMatrix() {
        //Nothing
    }
    // End: Constructor
    
    
    // - Start: Para antes de iniciar el jueego - 
    // Llena la matriz de piezas
    public int[][] CompletePieceMatrix() {
        if (gameStarted==false){
            ResetPieceMatrix();
        }else{
            if(maharajaReady){
               //Normal 
            }
        }
        return getPieceMatrix();
    }
    
    // Llena la matriz de coordenadas
    public int[][][] CompleteCooMatrix(){
        int cooX,cooY=13;
        for (int row =0; row < 8; row++) {
            cooY=cooY+55;
            cooX=42;
            for (int column = 0; column<8; column++){
                setCooCell(row,column,0,cooX);
                setCooCell(row,column,1,cooY);
                cooX=cooX+54;
            }
        }
        return getCooMatrix();
    }
    
    /*Solo se usa una vez*/
    public void MaharajaReady(){
        setPieceCell(getCurrentPositionCell(0), getCurrentPositionCell(1),6);
        maharajaReady=true;
        gameStarted=true;
        VideoJuego.messageForData="Maharajá colocado correctamente";
        VideoJuego.messageForData2="Ahora selecciona la ficha a mover";
    }
    
    // Reinicia el juego
    public void RestartGame(){
        temp=false;
        ResetPieceMatrix();
        deletePieceCellInAllMatrix(6);                              //Eliminamos al Maharaja
        
        currentPositionMatrix[0]=7;
        currentPositionMatrix[1]=3;
        maharajaReady=false;
        gameStarted=false;
    }
    
    // Reinicia las posiciones de las fichas
    public void ResetPieceMatrix() {
        if (temp==false){
            for (int row=0;row<8;row++){
                for (int column=0;column<8;column++){

                    if (row==1)                                             setPieceCell(row,column,0);  //Peon

                    else if ((row==0 && column==0)||(row==0 && column==7))  setPieceCell(row,column,1);  //Torre

                    else if ((row==0 && column==1)||(row==0 && column==6))  setPieceCell(row,column,2);  //Caballo

                    else if ((row==0 && column==2)||(row==0 && column==5))  setPieceCell(row,column,3);  //Alfil

                    else if (row==0 && column==3)                           setPieceCell(row,column,4);  //Reina

                    else if (row==0 && column==4)                           setPieceCell(row,column,5);  //Rey

                    else                                                    setPieceCell(row,column,7);  //Nada
                }
            }
        
            /* que se muestra un mensaje: seleccione el campo para poner el maharaja y presione Enter           */
            setCurrentPositionCell(6,4);    //Se determina la posición del navegador.
            temp=true;                      //Para que solo entre una vez
        }
    }
    // - End: Para antes de iniciar el jueego - 
    
    
    // - Start Maneja las posiciones de la pieza -
    public void ManagerMovePiece(){
        // 1. La posición del id del usuario cuando seleccionó la pieza queda en la matriz PosCurrentPieceBefore        
        //Si la pieza seleccionada es correcta
        if ( CorrectlySelectedPiece(getPieceCell(PosCurrentPieceBefore[0],PosCurrentPieceBefore[1])) == true){
            
            //Despues de seleccionar pieza
            if (awaitingMovement==true){
                //nothing
                VideoJuego.messageForData="Seleccione donde va a mover";
                VideoJuego.messageForData2="";
            }
            //Antes de seleccionar pieza
            else{
                //Se verifica si se puede MOVER
                if (VerifyMovePiece(getCurrentPositionCell(0),getCurrentPositionCell(1))==true){
                    NextPlayer();
                    //("Aviso interno: Pieza movida correctamente");
                }else{
                    awaitingMovement=false;
                    //("Aviso al jugador: Hubo un problema al mover la pieza");
                }
            }
        }else{
            //Si la pieza seleccionada es incorrecta
            awaitingMovement=false;
            VideoJuego.messageForData="La pieza seleccionada no es suya.";
        }
       //Antes de mover que verifique si está en jaque
        
    }
    
    //Verifica si la peza está en jaque o jaqueMate
    public boolean VerifyCheck(){
        return true;
    }
    
    //Verifica si el movimiento es válido y mueve la pieza.
    public boolean VerifyMovePiece(int rowToMove, int columnToMove){
        //return true: Si el movimiento fue hecho
        //rowFromMove   =PosCurrentPieceBefore[0];
        //columnFromMove=PosCurrentPieceBefore[1];
        //Si NOOO EXISTE UNA PIEZA en la casilla de ida entra aquí:
        if (CellIsEmpty(rowToMove,columnToMove)==true){
            /*
            Va moviendo UNA COPIA de la pieza por el tablero (excepto el caballo), 
            si no está bloqueda continua, sino retorna "false".
            */
            if (MovingPiece(PosCurrentPieceBefore[0],PosCurrentPieceBefore[1],rowToMove,columnToMove) == true){
                if (VideoJuego.CurrentPlayerIsMaharaja==true) {
                    VideoJuego.messageForData="¡Movida!. Ahora van los Cipayos";
                    VideoJuego.messageForData2="Seleccione un cipayo para mover";
                }else {
                    VideoJuego.messageForData="¡Movida!. Ahora van el Maharajá";
                    VideoJuego.messageForData2="Seleccione al Maharajá para mover";
                }
                return true;
            }else{
                //Habia una pieza en el camino.
                VideoJuego.messageForData="Hay una pieza bloqueando el camino";
                VideoJuego.messageForData2="O movimiento no pertenece a la ficha";
                return false;
            }
        }
        //Si EXISTE UNA PIEZA en la casilla de ida entra aquí:
        else{
            //Puede ser una casilla del mismo o otro player
            if (PieceInCellIsMy(rowToMove,columnToMove,VideoJuego.CurrentPlayerIsMaharaja) == true){
                VideoJuego.messageForData="Te vas a comer tu propia ficha? ._.";
                VideoJuego.messageForData2="Vuelve a seleccionar una ficha";
                return false;
            }else{
                //la quito y hafgo un if 
                int pieceCurrent = getPieceCell(rowToMove,columnToMove);
                //deletePieceCell(rowToMove,columnToMove);
                if (MovingPiece(PosCurrentPieceBefore[0],PosCurrentPieceBefore[1],rowToMove,columnToMove) == true){
                    VideoJuego.messageForData="Pieza movida";
                    VideoJuego.messageForData2="pieza del contrincante capturada";
                    return true;
                }else{
                    setPieceCell(rowToMove,columnToMove,pieceCurrent);
                    VideoJuego.messageForData="Hay una pieza bloqueando el camino";
                    return false;
                }
            }
        }
    }
    
    // de los métodos más grandes
    public boolean MovingPiece(int rowFrom, int columnFrom, int RowTo, int columnTo){
        switch(getPieceCell(rowFrom,columnFrom)){
            case 0:
                //Pieza de Peon
                if (PawnMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    if (rowFrom+1==RowTo && RowTo==7){
                        deletePieceCell(RowTo,columnTo);
                        setPieceCell(RowTo,columnTo,4);
                    }
                    return true;
                }return false;
            case 1:
                //Pieza de Torre
                if (StraightMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            case 2:
                //Pieza de Caballo
                if (KnightMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            case 3:
                //Pieza de Alfil
                if (DiagonalMovement( rowFrom+1,  columnFrom+1,  RowTo+1,  columnTo+1)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            case 4:
                //Pieza de Reina
                if (StraightMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true || DiagonalMovement( rowFrom+1,  columnFrom+1,  RowTo+1,  columnTo+1)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            case 5:
                //Pieza de Rey
                if (KingMovement(rowFrom,  columnFrom,  RowTo,  columnTo)==true){
                     MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            case 6:
                //Pieza de Maharaja
                if (StraightMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true || DiagonalMovement( rowFrom+1,  columnFrom+1,  RowTo+1,  columnTo+1)==true  || KnightMovement( rowFrom,  columnFrom,  RowTo,  columnTo)==true){
                    MovePiece(rowFrom,  columnFrom,  RowTo,  columnTo);
                    return true;
                }return false;
            default:
                System.out.println("Aviso al programador: Entro al default de movementPieces");
                return false;
        }
    }
    
    //Torre, Reina y Maharajá
    private boolean StraightMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        if (rowFrom==rowTo){                                            //Verticalmente
            if (columnFrom<columnTo){                                   //De izq a der
                for (int columnCurrent=columnFrom+1; columnCurrent!=columnTo; columnCurrent++){
                    if (CellIsEmpty(rowFrom,columnCurrent)==false) return false; //si no está vacía retorna false
                }return true;                                                    //si si está vacía retorna true
            }else{                                                      //de der a izq
                for (int columnCurrent=columnFrom-1; columnCurrent!=columnTo; columnCurrent--){
                    if (CellIsEmpty(rowFrom,columnCurrent)==false){
                     return false;} //si no está vacía retorna false
                }return true;                                                    //si si está vacía retorna true
            }
        }else if (columnFrom==columnTo){                                                          //Horizontalmente
            if (rowFrom<rowTo){                                         //De arr a aba
                for (int rowCurrent=rowFrom+1; rowCurrent!=rowTo; rowCurrent++){
                    if (CellIsEmpty(rowCurrent,columnFrom)==false) return false; //si no está vacía retorna false
                }return true;                                                    //si si está vacía retorna true
            }else{                                                      //de aba a arr
                for (int rowCurrent=rowTo+1; rowCurrent!=rowFrom; rowCurrent++){
                    if (CellIsEmpty(rowCurrent,columnFrom)==false) return false; //si no está vacía retorna false
                }return true;                                                    //si si está vacía retorna true
            }
        }else{
            return false;
        }
    }
    
    //Alfil, Reina y Maharajá
    private boolean DiagonalMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        int newColumn = 0;
            if (rowTo < rowFrom && columnTo < columnFrom) {         //Movimiento NOROESTE
            if ((rowTo - rowFrom) == (columnTo - columnFrom)) {
                rowFrom--;rowTo--;columnFrom--;
                for (int newRow = (rowFrom-1); newRow > rowTo; newRow--) {
                    newColumn = columnFrom - (rowFrom - newRow);
                    if (CellIsEmpty(newRow, newColumn)==false) {
                        return false;
                    }
                }return true;
            } else {
                return false;
            }
        }
        else if (rowTo < rowFrom && columnTo > columnFrom){         //Movimiento NORESTE
            if ((rowTo - rowFrom) == (columnFrom - columnTo)) {
                for (int newRow = (rowFrom - 1); newRow > rowTo; newRow--) {
                    newColumn = columnFrom + (rowFrom - newRow);
                    if (CellIsEmpty(newRow, newColumn)==false) {
                        return false;
                    }
                }return true;
            } else {
                return false;
            }
        } 
        else if (rowTo > rowFrom && columnTo < columnFrom){          //Movimiento SUROESTE
            if ((rowFrom - rowTo) == (columnTo - columnFrom)) {
                for (int newRow = (rowFrom + 1); newRow < rowTo; newRow++) {
                    newColumn = columnFrom - (newRow - rowFrom);
                    if (CellIsEmpty(newRow, newColumn)==false) {
                        return false;
                    }
                }return true;
            } else {
                return false;
            }
        } 
        else if (rowTo > rowFrom && columnTo > columnFrom){         //Movimiento SURESTE
            if ((rowFrom - rowTo) == (columnFrom - columnTo)) {
                for (int newRow = (rowFrom + 1); newRow < rowTo; newRow++) {
                    newColumn = columnFrom + (newRow - rowFrom);
                    if (CellIsEmpty(newRow, newColumn)==false) {
                        return false;
                    }
                }return true;
            } else {
                return false;
            }
        }
        return false;    
    }
    
    //Peon
    private boolean PawnMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        //si están en la celda row=1 puede mover 2!
        //Simpre verificando si row+1 y (column+1 o column-1) está el Maharajá
        if (rowFrom+1==rowTo && columnFrom+1==columnTo && getPieceCell(rowTo,columnTo)==6){
            //Captura al maharajá en diagonal.
            return true;
        }else if (rowFrom+1==rowTo && columnFrom-1==columnTo && CellIsEmpty(rowTo,columnTo)==false){
            //Captura al maharajá en diagonal.
            return true;
        }else if (rowFrom+1==rowTo && columnFrom==columnTo && CellIsEmpty(rowTo, columnTo)){
            return true;
        }else if (rowFrom==1 && columnFrom==columnTo && ((rowFrom+1==rowTo && CellIsEmpty(rowTo, columnTo)) || (rowFrom+2==rowTo && CellIsEmpty(rowTo-1, columnTo) && CellIsEmpty(rowTo, columnTo)))){
            return true;
        }else{
            return false;
        }
    }
    
    //Caballo y Maharajá
    private boolean KnightMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        if (rowFrom-2==rowTo && columnFrom+1==columnTo){              //sur
            return true;
        }else if (rowFrom-1==rowTo && columnFrom+2==columnTo){      //surEste
            return true;
        }else if (rowFrom+1==rowTo && columnFrom+2==columnTo){      //surOeste
            return true;
        }else if (rowFrom+2==rowTo && columnFrom+1==columnTo){        //este
            return true;
        }else if (rowFrom+2==rowTo && columnFrom-1==columnTo){      //oeste
            return true;
        }else if (rowFrom+1==rowTo && columnFrom-2==columnTo){        //norte
            return true;
        }else if (rowFrom-1==rowTo && columnFrom-2==columnTo){      //noreste
            return true;
        }else if (rowFrom-2==rowTo && columnFrom-1==columnTo){      //norOeste
            return true;
        }else {
            return false;
        }
    }
    
    //Rey
    private boolean KingMovement(int rowFrom, int columnFrom, int rowTo, int columnTo){
        if (rowFrom+1==rowTo && columnFrom==columnTo){              //sur
            return true;
        }else if (rowFrom+1==rowTo && columnFrom+1==columnTo){      //surEste
            return true;
        }else if (rowFrom+1==rowTo && columnFrom-1==columnTo){      //surOeste
            return true;
        }else if (rowFrom==rowTo && columnFrom+1==columnTo){        //este
            return true;
        }else if (rowFrom+1==rowTo && columnFrom-1==columnTo){      //oeste
            return true;
        }else if (rowFrom-1==rowTo && columnFrom==columnTo){        //norte
            return true;
        }else if (rowFrom-1==rowTo && columnFrom+1==columnTo){      //noreste
            return true;
        }else if (rowFrom-1==rowTo && columnFrom-1==columnTo){      //norOeste
            return true;
        }else {
            return false;
        }
    }
    
    //Verifica si la pieza que hay en la posicion pasada por parametro es del player o no.
    public boolean PieceInCellIsMy(int row, int column, boolean playerMaharaja){
        if (getPieceCell(row, column)!=6 && playerMaharaja==false){
            //la pieza es de Cipayos
            return true;
        }else if (getPieceCell(row, column)==6 && playerMaharaja==true){
            //la pieza es de Maharaja
            return true;
        }else {
            //La pieza no es del jugador
            return false;
        }
    }
    
    // cambia una pieza de lugar
    public void MovePiece(int rowOld, int columnOld, int rowNew, int columnNew){
        int pieceToMove= getPieceCell(rowOld,columnOld);
        deletePieceCell(rowOld,columnOld);
        setPieceCell(rowNew,columnNew,pieceToMove);
    }
    
    private boolean CorrectlySelectedPiece(int currentPiece){
        if (currentPiece==7){
            VideoJuego.messageForData="No hay pieza en esa posición";
            return false;
        }else if (currentPiece==6 && VideoJuego.CurrentPlayerIsMaharaja==true){
            return true;
        }else if (currentPiece!=6 && VideoJuego.CurrentPlayerIsMaharaja==false){
            return true;
        }else{
            VideoJuego.messageForData="La pieza seleccionda no es suya";
            return false;
        }   
    }
    
    public void NextPlayer(){
        if (VideoJuego.CurrentPlayerIsMaharaja==false) VideoJuego.CurrentPlayerIsMaharaja=true;
        else VideoJuego.CurrentPlayerIsMaharaja=false;
    }
    
    public void MaharajaOff() {
        maharajaInBoard=false;
    }
    // - End: Maneja las posiciones de la pieza -
    
    
    // - Start: Get y Sets -
    public int getCooCell(int row, int column,int coo) {
        return cooMatrix[row][column][coo];
    }
    
    public int getPieceCell(int row, int column) {
        return pieceMatrix[row][column];
    }
    
    public void setCooCell(int row, int column, int coo, int coordenada) {
        cooMatrix[row][column][coo] = coordenada;
    }
    
    public void setPieceCell(int row, int column, int piece) {
        pieceMatrix[row][column] = piece;
    }
    
    public int[][][] getCooMatrix() {
        return cooMatrix;
    }
    
    public int[][] getPieceMatrix() {
        return pieceMatrix;
    }
    
    public void setCooMatrix(int [][][] matrix){
        cooMatrix= matrix;
    }
    
    public void setPieceMatrix(int [][] matrix){
        pieceMatrix= matrix; 
    }
    
    public void deletePieceCell(int row,int column){
        setPieceCell(row,column,7);
    }
    
    public void deletePieceCellInAllMatrix(int piece){
        for (int row=0;row<8;row++){
            for (int column=0;column<8;column++){
                if (piece==getPieceCell(row,column)){
                    setPieceCell(row,column,7);
                }
            }
        }
    }
    
    public boolean CellIsEmpty(int row, int column){
        if (getPieceCell(row,column)==7){
            return true;
        }else{
            return false;
        }
    }
    
    public void GameStartedOff(){
        gameStarted=false;
    }
    
    public void GameStartedOn(){
        gameStarted=true;
    }
    
    public boolean gameIsStart(){
        return gameStarted;
    }
    // - End: Get y Sets -
    
    
    public boolean checkExistKing() {
        
        for (int row = 0; row < 8; row++) {
            
            for (int column = 0; column < 8; column++) {
                
                if (pieceMatrix[row][column] == 5) {            //Si el rey todaviía existe
                    return true;
                }   
            }   
        }
        return false;
    }
    
        public boolean checkExistMaharaja() {
        
        for (int row = 0; row < 8; row++) {
            
            for (int column = 0; column < 8; column++) {
                
                if (pieceMatrix[row][column] == 6) {            //Si el maharaja todaviía existe
                    return true;
                }   
            }   
        }
        return false;
    }
    
    /* Start: Para mover el inidicador del usuario ------------------------------------- */ 
    public int getCurrentPositionCell(int row_column){
        return currentPositionMatrix[row_column];
    }
   
    public void setCurrentPositionCell(int row, int column) {
        currentPositionMatrix[0]=row;
        currentPositionMatrix[1]=column;
    }
    
    public void currentPositionRight(){
        if (currentPositionMatrix[1]<7 ){
            currentPositionMatrix[1]++;
        }
    }

    public void currentPositionLeft(){
        if (currentPositionMatrix[1]>0){
            currentPositionMatrix[1]--;
        }
    }

    public void currentPositionDown(){
        if (currentPositionMatrix[0]<7){
            currentPositionMatrix[0]++;
        }
    }
    
    public void currentPositionUp(){
        if (maharajaReady==false ){
            if (currentPositionMatrix[0]==7 || currentPositionMatrix[0]==6){
                currentPositionMatrix[0]--;
            }else if (currentPositionMatrix[0]==5){
                //Nothing
            }
        }
        else if (maharajaReady==true  && currentPositionMatrix[0]>0){
            currentPositionMatrix[0]--;
        }
    }
    /* End: Para mover el inidicador del usuario ------------------------------------- */ 

    

    
}
