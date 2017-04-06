<div class="container col-md-12" style="margin-top:10px">
    <div class="col-md-2 pull-left">
    <span class="list-group col-md-10" id="requestList">
        <a href="#" class="list-group-item active" style="border-color: #4c9662; background-color: #4c9662;">
            Available requests
        </a>
    </span>
        <div class="md-form">
            <input type="text" class="form-control col-md-10" id="modelNameInput" value="">
            <button class="btn btn-success waves-effect col-md-10" onclick="generateModel()">create model</button>
            <input type="text" class="form-control col-md-10" id="repositoryNameInput" value="">
            <button class="btn btn-success waves-effect col-md-10" onclick="generateRepository()">create repository
            </button>

            <div class="form-group">
            <input type="text" class="form-control-inline col-md-8" id="actionNameInput" value="">
            <input type="text" class="form-control-inline col-md-8" id="messageTypeInput" value="100500">
       
            <button class="btn btn-success waves-effect col-md-10" onclick="generateAction()">create action
            </button>
            </div>
        </div>

    </div>
    <div class="col-md-10 pull-right">
        <div class="card col-md-8">
            <div class="card-header primary-color white-text">
                SOGE Http
            </div>
            </div>

            <h5>Request</h5>
            <div class="md-form">
               <textarea type="text" id="requestData"
                         class="md-textarea col-md-10">{"messageType":1,"data":null}</textarea>
                <button class="btn btn-info pull-right waves-effect" id="send_btn" onclick="sendRequest()">Send</button>
            </div>
        </div>

        <div class="card col-md-8">
            <div class="card-header blue-grey white-text">Received messages</div>
            <div class="card-block" style="height: 350px">
                <ul id="logList" style="height: 300px; overflow: auto">
                </ul>
            </div>
        </div>
</div>