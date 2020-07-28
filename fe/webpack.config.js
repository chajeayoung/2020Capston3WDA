// var packageJSON = require('./package.json');
// var path = require('path');
// var webpack = require('webpack');

// const PATHS = {
//   build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
// };

// module.exports = {
//   entry: './app/index.js',

//   output: {
//     path: PATHS.build,
//     filename: 'app-bundle.js'
//   }
// };

const packageJSON = require("./package.json");
const path = require("path");
const webpack = require("webpack");
// const CleanWebpackPlugin = require('clean-webpack-plugin');

const PATHS = {
  build: path.resolve(
    __dirname,
    "target",
    "classes",
    "META-INF",
    "resources",
    "webjars",
    packageJSON.name,
    packageJSON.version
  ),
};

module.exports = {
  entry: {
    home: path.join(__dirname, "./app/home.js"),
    tt: path.join(__dirname, "./app/tt.js"),
    register: path.join(__dirname, "./app/register.js"),
    login: path.join(__dirname, "./app/login.js"),
    voteIndex: path.join(__dirname, "./app/vote/voteIndex.jsx"),
    voteCreate: path.join(__dirname, "./app/vote/voteCreate.jsx"),
    voteShow: path.join(__dirname, "./app/vote/voteShow.jsx"),
    voteResult: path.join(__dirname, "./app/vote/voteResult.jsx"),
    layout: path.join(__dirname, "./app/layout/layout.jsx"),
    userInfo: path.join(__dirname, "./app/userInfo/userInfo.jsx"),
    myProgram: path.join(__dirname, "./app/userInfo/myProgram.jsx"),
    profileIndex: path.join(__dirname, "./app/userInfo/profileIndex.jsx"),
    allCompany: path.join(__dirname, "./app/userInfo/allCompany.jsx"),
    proRegIndex: path.join(__dirname, "./app/programRegister/proRegIndex.js"),
    modal: path.join(__dirname, "./app/programRegister/modal.jsx"),
    votePreShow: path.join(__dirname, "./app/vote/votePreShow.jsx"),
    voteEndShow: path.join(__dirname, "./app/vote/voteEndShow.jsx"),
    Shop_index: path.join(__dirname, "./app/Shop_index.jsx"),
    communityIndex: path.join(__dirname, "./app/community/index.js"),
    detailIndex: path.join(__dirname, "./app/community/detailIndex.js"),
    voterVoteList: path.join(__dirname, "./app/userInfo/voterVoteList.jsx"),
    manageVote: path.join(__dirname, "./app/userInfo/manageVote.jsx"),
    blockChainIntroduce: path.join(
      __dirname,
      "./app/introduce/blockChainIntroduce.js"
    ),
    shopItemCreate: path.join(__dirname, "./app/shop/itemCreate.jsx"),
    myCommunity: path.join(__dirname, "./app/userInfo/myCommunity.jsx"),
    updatePopular: path.join(__dirname, "./app/userInfo/updatePopular.jsx"),
    popularBoard: path.join(__dirname, "./app/community/popularBoard.jsx"),
    popularBoardView: path.join(
      __dirname,
      "./app/community/popularBoardView.jsx"
    ),
    popularBoardCreate: path.join(
      __dirname,
      "./app/community/popularBoardCreate.jsx"
    ),
    managePrd: path.join(__dirname, "./app/userInfo/managePrd.jsx"),
    prdShow: path.join(__dirname, "./app/shop/prdShow.jsx"),
    shopItemEdit: path.join(__dirname, "./app/shop/itemEdit.jsx"),
    mybag: path.join(__dirname, "./app/shop/mybag.jsx"),
    order: path.join(__dirname, "./app/shop/order.jsx"),
    buySuccess: path.join(__dirname, "./app/shop/buySuccess.jsx"),
    orderList: path.join(__dirname, "./app/shop/orderList.jsx"),
    orderShow: path.join(__dirname, "./app/shop/orderShow.jsx"),
    manageOrder: path.join(__dirname, "./app/userInfo/manageOrder.jsx"),
    manageOrderState: path.join(
      __dirname,
      "./app/userInfo/manageOrderState.jsx"
    ),
    Shop_list: path.join(__dirname, "./app/shop/Shop_list.jsx"),
    buyError: path.join(__dirname, "./app/shop/buyError.jsx"),
    managerIndex: path.join(__dirname, "./app/riroIndex/managerIndex.jsx"),
    userIndex: path.join(__dirname, "./app/riroIndex/userIndex.jsx"),
    myAudition: path.join(__dirname, "./app/userInfo/myAudition.jsx"),
    audience: path.join(__dirname, "./app/audience/audience.jsx"),
 
    hotclib: path.join(__dirname, "./app/hotclib/hotclib.jsx"),
    hotclibread: path.join(__dirname, "./app/hotclib/hotclibread.jsx"),
    hotclibupload: path.join(__dirname, "./app/hotclib/hotclibupload.jsx"),
  },
  output: {
    path: PATHS.build,
    filename: "[name].js",
    publicPath: "/assets/",
  },
  // resolve:{
  //   extensions: ['','.js','.jsx']
  // },
  devServer: {
    hot: true,
    historyApiFallback: true,
    inline: true,
    port: 3000,
    publicPath: "/assets/",
    // contentBase: './tmp',
    // webpack외부접속
    disableHostCheck:true,
    host:"0.0.0.0",
    proxy: {
      "**": "http://localhost:8000",
    },
    
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        // exclude: /(node_modules|bower_components)/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
          options: {
            cacheDirectory: true,
            presets: ["env", "react", "babel-polyfill"],
          },
        },
      },
      {
        test: /.jsx$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
          query: {
            presets: ["env", "react", "babel-polyfill"],
          },
        },
        //
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: ["file-loader"],
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: ["file-loader"],
      },
    ],
  },
  devtool: "inline-source-map",
  plugins: [
    // new CleanWebpackPlugin([PATHS.build])
    new webpack.HotModuleReplacementPlugin(),
    // ["@babel/plugin-transform-runtime"]
    // ["@babel/plugin-transform-runtime",
    //   {
    //     "regenerator": true
    //   }
    // ]
  ],
};
