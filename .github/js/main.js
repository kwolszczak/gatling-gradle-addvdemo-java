const core = require('@actions/core');
//const github = require('@actions/github');
const exec = require('@actions/exec');

// run first in this directory:
//npm install @actions/core @actions/github @actions/exec
// add folders to git, they should be uploaded !!!

function run() {
  core.notice('Hello from my custom action')

  // 1) get some input values
  const bucket = core.getInput('bucket', {required: true });
  const bucketRegion = core.getInput('bucket-region', {required: false});
  const distFolder = core.getInput('dist-folder', {required: true});

  // 2) upload file
  // on linux runner there is preinstalled aws CLI to run commands
  // zwroc uwage na cudzyslow ` pochylony
  const s3Uri = `s3://${bucket}`
  exec.exec(`aws s3 sync ${distFolder} ${s3Uri} --region ${bucketRegion}`)

  const websiteUrl = `http://${bucket}.s3-website-${bucketREgion}.amazonaws.com`;
  core.setOutput('websiteUrl', websiteUrl);
}

run()