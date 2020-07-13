import React from 'react';
import Chart from '@bit/nexxtway.react-rainbow.chart';
import Dataset from '@bit/nexxtway.react-rainbow.dataset';

const containerStyles = {
  width: 600,
  margin: "auto"
};

// npm i @bit/nexxtway.react-rainbow.chart
export default class LineChartExample extends React.Component {
    constructor(props) {
        super(props);
        this.titles = ['Data-Yellow', 'Data-Green', 'Data-Orange', 'Data-Purple', 'Data-Dark'];
        this.colors = ['#ffcc00', '#1ad1a3', '#ff6837', '#663398', '#061c3f'];
        this.months = ['5달전', '4달전', '3달전', '2달전', '1달전', '총합'];
        this.state = {
            labels: ['5 ~ 4달 전', '4 ~ 3달 전', '3 ~ 2달 전', '2 ~ 1달 전', '1달전 ~ 현재'],
            datasets: [
                {
                    title: 'Data-Red',
                    borderColor: '#fe4849',
                    values: [0,0,0,0,0],
                },
                {
                    title: 'Data-Blue',
                    borderColor: '#01b6f5',
                    values: [0,0,0,0,0],
                },
            ],
        };
    }

   

    renderDatasets(datasets) {
        // var { datasets } = this.props.data;
        // console.log(datasets)
        datasets.map(({ title, values, borderColor }) => (
            console.log(title,values,borderColor)
        ));
        return datasets.map(({ title, values, borderColor }) => (
            
            <Dataset
                key={title}
                title={title}
                values={values}
                borderColor={borderColor}
                backgroundColor={borderColor}
            />
        ));
    }

    render() {
        var { labels } = this.props.data;
        var { datasets } = this.props.data;
        console.log(this.props.data);
        return (
            <div>
                <div id="graph_chart" style={containerStyles}>
                    <Chart labels={labels} type="line">
                        {this.renderDatasets(datasets)}
                    </Chart>
                </div>
            </div>
        );
    }
}