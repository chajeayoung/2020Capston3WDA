// package riro;

// import com.klaytn.caver.Caver;
// import com.klaytn.caver.crpyto.KlayCredentials;
// import com.klaytn.caver.methods.response.KlayTransactionReceipt;
// import com.klaytn.caver.tx.SmartContract;
// import com.klaytn.caver.tx.manager.TransactionManager;
// import java.math.BigInteger;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.concurrent.Callable;
// import org.web3j.abi.TypeReference;
// import org.web3j.abi.datatypes.Address;
// import org.web3j.abi.datatypes.DynamicArray;
// import org.web3j.abi.datatypes.Function;
// import org.web3j.abi.datatypes.Type;
// import org.web3j.abi.datatypes.generated.Uint256;
// import org.web3j.protocol.core.RemoteCall;
// import org.web3j.tuples.generated.Tuple2;
// import org.web3j.tuples.generated.Tuple5;
// import org.web3j.tuples.generated.Tuple9;
// import org.web3j.tx.gas.ContractGasProvider;

// /**
//  * <p>Auto generated smart contract code.
//  * <p><strong>Do not modify!</strong>
//  */
// public class Riro720m extends SmartContract {
//     private static final String BINARY = "60806040526001805560006002556003805534801561001d57600080fd5b50600080546001600160a01b031916331781556001556112da806100426000396000f3fe6080604052600436106100f35760003560e01c80638da5cb5b1161008a578063b5aa89eb11610059578063b5aa89eb1461028f578063d0e30db0146102b9578063de8fa431146102c1578063e9ab77e5146102d6576100f3565b80638da5cb5b1461020f5780639bd575c214610231578063a87d942c14610254578063a998590c14610269576100f3565b806322434836116100c6578063224348361461018d57806350c42c78146101ad5780636eea5251146101cf5780637804f3c1146101ef576100f3565b806312065fe0146100f557806312514bba14610120578063170ab4051461014d57806317242e8b1461016d575b005b34801561010157600080fd5b5061010a6102f9565b60405161011791906111ea565b60405180910390f35b34801561012c57600080fd5b5061014061013b366004610e9e565b6102fe565b60405161011791906111dc565b34801561015957600080fd5b50610140610168366004610e9e565b61034a565b34801561017957600080fd5b50610140610188366004610efe565b61055c565b34801561019957600080fd5b506101406101a8366004610ec4565b6105e0565b3480156101b957600080fd5b506101c26105f3565b604051610117919061107e565b3480156101db57600080fd5b506101406101ea366004610f59565b61064b565b3480156101fb57600080fd5b5061014061020a366004610e3a565b6106ca565b34801561021b57600080fd5b5061022461085e565b6040516101179190611070565b34801561023d57600080fd5b5061024661086d565b60405161011792919061108f565b34801561026057600080fd5b5061010a610920565b34801561027557600080fd5b5061027e610926565b6040516101179594939291906110b4565b34801561029b57600080fd5b506102a4610ae3565b60405161011799989796959493929190611120565b6100f3610db1565b3480156102cd57600080fd5b5061010a610dca565b3480156102e257600080fd5b506102eb610dd0565b6040516101179291906111f8565b303190565b6000816103096102f9565b101561031457600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610341573d6000803e3d6000fd5b50600192915050565b60005b600454821461038d576004805460018101825560009182527f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b015561034d565b5b60055482146103ce576005805460018101825560009182527f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0015561038e565b5b600654821461040f576006805460018101825560009182527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f01556103cf565b5b6007548214610450576007805460018101825560009182527fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c6880155610410565b5b6008548214610491576008805460018101825560009182527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30155610451565b5b60095482146104d2576009805460018101825560009182527f6e1540171b6c0c960b71a7020d9f60077f6af931a8bbf590da0223dacf75c7af0155610492565b5b600a54821461051357600a805460018101825560009182527fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a801556104d3565b5b600b54821461055457600b805460018101825560009182527f0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01db90155610514565b506001919050565b600061056884846105e0565b50816000805b82518210156105c757600083838151811061058557fe5b602001015160f81c60f81b60f81c60ff169050603081101580156105aa575060398111155b156105bb576030810382600a020191505b5060019091019061056e565b6105d08161034a565b50600193505050505b9392505050565b6002829055600381905560015b92915050565b6060600480548060200260200160405190810160405280929190818152602001828054801561064157602002820191906000526020600020905b81548152602001906001019080831161062d575b5050505050905090565b6000846002541115801561066157506003548511155b156106bd578561066f6102f9565b101561067a57600080fd5b604051339087156108fc029088906000818181858888f193505050501580156106a7573d6000803e3d6000fd5b506106b38285856106ca565b50600190506106c1565b5060005b95945050505050565b60008381805b82518210156107295760008383815181106106e757fe5b602001015160f81c60f81b60f81c60ff1690506030811015801561070c575060398111155b1561071d576030810382600a020191505b506001909101906106d0565b6004600182038154811061073957fe5b60009182526020909120018054600190810190915586141561077c576005600182038154811061076557fe5b6000918252602090912001805460010190556107f3565b8560021415610795576006600182038154811061076557fe5b85600314156107ae576007600182038154811061076557fe5b85600414156107c7576008600182038154811061076557fe5b85600514156107f357600960018203815481106107e057fe5b6000918252602090912001805460010190555b8461081f57600a600182038154811061080857fe5b60009182526020909120018054600101905561084b565b846001141561084b57600b600182038154811061083857fe5b6000918252602090912001805460010190555b5050600180548101815595945050505050565b6000546001600160a01b031681565b606080600a600b818054806020026020016040519081016040528092919081815260200182805480156108bf57602002820191906000526020600020905b8154815260200190600101908083116108ab575b505050505091508080548060200260200160405190810160405280929190818152602001828054801561091157602002820191906000526020600020905b8154815260200190600101908083116108fd575b50505050509050915091509091565b60015490565b6060806060806060600560066007600860098480548060200260200160405190810160405280929190818152602001828054801561098357602002820191906000526020600020905b81548152602001906001019080831161096f575b50505050509450838054806020026020016040519081016040528092919081815260200182805480156109d557602002820191906000526020600020905b8154815260200190600101908083116109c1575b5050505050935082805480602002602001604051908101604052809291908181526020018280548015610a2757602002820191906000526020600020905b815481526020019060010190808311610a13575b5050505050925081805480602002602001604051908101604052809291908181526020018280548015610a7957602002820191906000526020600020905b815481526020019060010190808311610a65575b5050505050915080805480602002602001604051908101604052809291908181526020018280548015610acb57602002820191906000526020600020905b815481526020019060010190808311610ab7575b50505050509050945094509450945094509091929394565b6060806060806060806060806000600460056006600760086009600a600b60015488805480602002602001604051908101604052809291908181526020018280548015610b4f57602002820191906000526020600020905b815481526020019060010190808311610b3b575b5050505050985087805480602002602001604051908101604052809291908181526020018280548015610ba157602002820191906000526020600020905b815481526020019060010190808311610b8d575b5050505050975086805480602002602001604051908101604052809291908181526020018280548015610bf357602002820191906000526020600020905b815481526020019060010190808311610bdf575b5050505050965085805480602002602001604051908101604052809291908181526020018280548015610c4557602002820191906000526020600020905b815481526020019060010190808311610c31575b5050505050955084805480602002602001604051908101604052809291908181526020018280548015610c9757602002820191906000526020600020905b815481526020019060010190808311610c83575b5050505050945083805480602002602001604051908101604052809291908181526020018280548015610ce957602002820191906000526020600020905b815481526020019060010190808311610cd5575b5050505050935082805480602002602001604051908101604052809291908181526020018280548015610d3b57602002820191906000526020600020905b815481526020019060010190808311610d27575b5050505050925081805480602002602001604051908101604052809291908181526020018280548015610d8d57602002820191906000526020600020905b815481526020019060010190808311610d79575b50505050509150985098509850985098509850985098509850909192939495969798565b6000546001600160a01b03163314610dc857600080fd5b565b60045490565b6002546003549091565b600082601f830112610deb57600080fd5b8135610dfe610df98261123a565b611213565b91508082526020830160208301858383011115610e1a57600080fd5b610e25838284611294565b50505092915050565b60006105d98235611291565b600080600060608486031215610e4f57600080fd5b833567ffffffffffffffff811115610e6657600080fd5b610e7286828701610dda565b9350506020610e8386828701610e2e565b9250506040610e9486828701610e2e565b9150509250925092565b600060208284031215610eb057600080fd5b6000610ebc8484610e2e565b949350505050565b60008060408385031215610ed757600080fd5b6000610ee38585610e2e565b9250506020610ef485828601610e2e565b9150509250929050565b600080600060608486031215610f1357600080fd5b6000610f1f8686610e2e565b9350506020610f3086828701610e2e565b925050604084013567ffffffffffffffff811115610f4d57600080fd5b610e9486828701610dda565b600080600080600060a08688031215610f7157600080fd5b6000610f7d8888610e2e565b9550506020610f8e88828901610e2e565b9450506040610f9f88828901610e2e565b9350506060610fb088828901610e2e565b925050608086013567ffffffffffffffff811115610fcd57600080fd5b610fd988828901610dda565b9150509295509295909350565b6000610ff28383611067565b505060200190565b61100381611275565b82525050565b600061101482611268565b61101e818561126c565b935061102983611262565b60005b828110156110545761103f868351610fe6565b955061104a82611262565b915060010161102c565b5093949350505050565b61100381611280565b61100381611291565b602081016105ed8284610ffa565b602080825281016105d98184611009565b604080825281016110a08185611009565b90508181036020830152610ebc8184611009565b60a080825281016110c58188611009565b905081810360208301526110d98187611009565b905081810360408301526110ed8186611009565b905081810360608301526111018185611009565b905081810360808301526111158184611009565b979650505050505050565b6101208082528101611132818c611009565b90508181036020830152611146818b611009565b9050818103604083015261115a818a611009565b9050818103606083015261116e8189611009565b905081810360808301526111828188611009565b905081810360a08301526111968187611009565b905081810360c08301526111aa8186611009565b905081810360e08301526111be8185611009565b90506111ce610100830184611067565b9a9950505050505050505050565b602081016105ed828461105e565b602081016105ed8284611067565b604081016112068285611067565b6105d96020830184611067565b60405181810167ffffffffffffffff8111828210171561123257600080fd5b604052919050565b600067ffffffffffffffff82111561125157600080fd5b506020601f91909101601f19160190565b60200190565b5190565b90815260200190565b60006105ed82611285565b151590565b6001600160a01b031690565b90565b8281833750600091015256fea265627a7a723058208bdb31c73e809c711ff02dbfda00a127d0d80f016cfd0edee07867c23d9e07806c6578706572696d656e74616cf50037";

//     public static final String FUNC_GETBALANCE = "getBalance";

//     public static final String FUNC_TRANSFER = "transfer";

//     public static final String FUNC_SETSIZE = "setSize";

//     public static final String FUNC_SETOPTIONS = "setOptions";

//     public static final String FUNC_SETTIMES = "setTimes";

//     public static final String FUNC_GETLISTVOTE = "getListVote";

//     public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

//     public static final String FUNC_LISTVOTE = "listVote";

//     public static final String FUNC_OWNER = "owner";

//     public static final String FUNC_GETLISTGENDER = "getListGender";

//     public static final String FUNC_GETCOUNT = "getCount";

//     public static final String FUNC_GETLISTAGE = "getListAge";

//     public static final String FUNC_GETLISTTOTAL = "getListTotal";

//     public static final String FUNC_DEPOSIT = "deposit";

//     public static final String FUNC_GETSIZE = "getSize";

//     public static final String FUNC_GETTIMES = "getTimes";

//     protected Riro720m(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
//     }

//     protected Riro720m(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         super(BINARY, contractAddress, caver, transactionManager, contractGasProvider);
//     }

//     public RemoteCall<BigInteger> getBalance() {
//         final Function function = new Function(FUNC_GETBALANCE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transfer(BigInteger _value) {
//         final Function function = new Function(
//                 FUNC_TRANSFER, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setSize(BigInteger _limit) {
//         final Function function = new Function(
//                 FUNC_SETSIZE, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setOptions(BigInteger _startTime, BigInteger _endTime, String _limit) {
//         final Function function = new Function(
//                 FUNC_SETOPTIONS, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_endTime), 
//                 new org.web3j.abi.datatypes.Utf8String(_limit)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setTimes(BigInteger _startTime, BigInteger _endTime) {
//         final Function function = new Function(
//                 FUNC_SETTIMES, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_endTime)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<List> getListVote() {
//         final Function function = new Function(FUNC_GETLISTVOTE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
//         return new RemoteCall<List>(
//                 new Callable<List>() {
//                     @Override
//                     @SuppressWarnings("unchecked")
//                     public List call() throws Exception {
//                         List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
//                         return convertToNative(result);
//                     }
//                 });
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transferWithData(BigInteger _value, BigInteger _time, BigInteger _uAge, BigInteger _uGender, String _val) {
//         final Function function = new Function(
//                 FUNC_TRANSFERWITHDATA, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_time), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uGender), 
//                 new org.web3j.abi.datatypes.Utf8String(_val)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> listVote(String num, BigInteger _uAge, BigInteger _uGender) {
//         final Function function = new Function(
//                 FUNC_LISTVOTE, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(num), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uGender)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<String> owner() {
//         final Function function = new Function(FUNC_OWNER, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
//         return executeRemoteCallSingleValueReturn(function, String.class);
//     }

//     public RemoteCall<Tuple2<List<BigInteger>, List<BigInteger>>> getListGender() {
//         final Function function = new Function(FUNC_GETLISTGENDER, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
//         return new RemoteCall<Tuple2<List<BigInteger>, List<BigInteger>>>(
//                 new Callable<Tuple2<List<BigInteger>, List<BigInteger>>>() {
//                     @Override
//                     public Tuple2<List<BigInteger>, List<BigInteger>> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple2<List<BigInteger>, List<BigInteger>>(
//                                 convertToNative((List<Uint256>) results.get(0).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(1).getValue()));
//                     }
//                 });
//     }

//     public RemoteCall<BigInteger> getCount() {
//         final Function function = new Function(FUNC_GETCOUNT, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> getListAge() {
//         final Function function = new Function(FUNC_GETLISTAGE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
//         return new RemoteCall<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
//                 new Callable<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
//                     @Override
//                     public Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
//                                 convertToNative((List<Uint256>) results.get(0).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(1).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(2).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(3).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(4).getValue()));
//                     }
//                 });
//     }

//     public RemoteCall<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>> getListTotal() {
//         final Function function = new Function(FUNC_GETLISTTOTAL, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Uint256>() {}));
//         return new RemoteCall<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>>(
//                 new Callable<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>>() {
//                     @Override
//                     public Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>(
//                                 convertToNative((List<Uint256>) results.get(0).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(1).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(2).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(3).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(4).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(5).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(6).getValue()), 
//                                 convertToNative((List<Uint256>) results.get(7).getValue()), 
//                                 (BigInteger) results.get(8).getValue());
//                     }
//                 });
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> deposit(BigInteger pebValue) {
//         final Function function = new Function(
//                 FUNC_DEPOSIT, 
//                 Arrays.<Type>asList(), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function, pebValue);
//     }

//     public RemoteCall<BigInteger> getSize() {
//         final Function function = new Function(FUNC_GETSIZE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<Tuple2<BigInteger, BigInteger>> getTimes() {
//         final Function function = new Function(FUNC_GETTIMES, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
//         return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
//                 new Callable<Tuple2<BigInteger, BigInteger>>() {
//                     @Override
//                     public Tuple2<BigInteger, BigInteger> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple2<BigInteger, BigInteger>(
//                                 (BigInteger) results.get(0).getValue(), 
//                                 (BigInteger) results.get(1).getValue());
//                     }
//                 });
//     }

//     public static Riro720m load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         return new Riro720m(contractAddress, caver, credentials, chainId, contractGasProvider);
//     }

//     public static Riro720m load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         return new Riro720m(contractAddress, caver, transactionManager, contractGasProvider);
//     }

//     public static RemoteCall<Riro720m> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         return deployRemoteCall(Riro720m.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
//     }

//     public static RemoteCall<Riro720m> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         return deployRemoteCall(Riro720m.class, caver, transactionManager, contractGasProvider, BINARY, "");
//     }
// }
